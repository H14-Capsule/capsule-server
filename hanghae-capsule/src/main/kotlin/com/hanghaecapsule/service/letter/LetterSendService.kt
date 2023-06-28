package com.hanghaecapsule.service.letter

import com.hanghaecapsule.domain.author.AuthorRepository
import com.hanghaecapsule.domain.letter.LetterRepository
import com.hanghaecapsule.service.external.EmailSender
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LetterSendService(
        private val emailSender: EmailSender,
        private val letterRepository: LetterRepository,
        private val authorRepository: AuthorRepository,
        private val letterFactory: LetterFactory, // TODO:  구현체
) {
    fun sendLetters(targetSendDate: LocalDate) {
        val letters = letterRepository.findAllBySendDate(targetSendDate)
        val authors = authorRepository.findAllById(letters.map { it.authorId })
                .associateBy { it.id }

        letters.forEach {
            val pdfLetter = letterFactory.generate(
                    author = authors[it.authorId]
                            ?: throw IllegalArgumentException("존재하지 않는 작성자입니다. authorId = ${it.authorId}"),
                    letter = it,
            )

            TODO("생성한 pdfLetter를 전송하는 기능")
        }
    }
}