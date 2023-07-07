package com.hanghaecapsule.service.letter

import com.hanghaecapsule.domain.author.AuthorRepository
import com.hanghaecapsule.domain.letter.LetterRepository
import com.hanghaecapsule.domain.letter.SendStatus
import com.hanghaecapsule.service.external.EmailSender
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.transaction.Transactional

@Service
class LetterSendService(
    private val emailSender: EmailSender,
    private val letterRepository: LetterRepository,
    private val authorRepository: AuthorRepository,
    private val letterFactory: LetterFactory, // TODO:  구현체
) {
    @Transactional
    fun sendLetters(targetSendDate: LocalDate) {
        val targetLetters = letterRepository.findAllBySendDateAndSendStatus(
            sendDate = targetSendDate,
            sendStatus = SendStatus.WAIT,
        )
        val authors = authorRepository.findAllById(targetLetters.map { it.authorId })
            .associateBy { it.id }

        targetLetters.forEach {
            val author =
                authors[it.authorId] ?: throw IllegalArgumentException("존재하지 않는 작성자입니다. authorId = ${it.authorId}")
            val pdfLetter = letterFactory.generate(
                author = author,
                letter = it,
            )

            emailSender.sendLetter(pdfLetter, author.email)
            val sendLetter = targetLetters.find { letter -> letter.authorId == it.authorId }!!
            sendLetter.doneSend()
        }
    }
}