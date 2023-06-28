package com.hanghaecapsule.service.letter

import com.hanghaecapsule.domain.letter.LetterRepository
import com.hanghaecapsule.service.external.EmailSender
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LetterSendService(
        private val emailSender: EmailSender,
        private val letterRepository: LetterRepository,
        private val authorRepository: LetterRepository,
        private val letterFactory: LetterFactory,
) {
    fun sendLetters(targetSendDate: LocalDate) {
        val letters = letterRepository.findAllBySendDate(targetSendDate)
        val authors = authorRepository.findAllById(letters.map { it.authorId })
                .associateBy { it.authorId }


    }
}