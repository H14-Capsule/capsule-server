package com.hanghaecapsule.service.external

import com.hanghaecapsule.domain.letter.PDFLetter

interface EmailSender {
    fun send(email: String, authKey: String)
    fun sendLetter(capsuleLetter: PDFLetter, email: String)
}
