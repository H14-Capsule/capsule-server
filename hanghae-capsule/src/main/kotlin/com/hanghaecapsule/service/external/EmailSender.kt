package com.hanghaecapsule.service.external

interface EmailSender {
    fun send(email: String, authKey: String)

//    fun sendLetter(email: String, letter: PDFLetter)
}
