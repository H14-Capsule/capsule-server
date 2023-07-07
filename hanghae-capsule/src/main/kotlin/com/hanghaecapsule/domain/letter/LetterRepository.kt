package com.hanghaecapsule.domain.letter

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface LetterRepository : JpaRepository<Letter, Long> {
    fun findAllBySendDateAndSendStatus(sendDate: LocalDate, sendStatus: SendStatus): List<Letter>
}
