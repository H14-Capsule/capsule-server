package com.hanghaecapsule.service.external

interface EmailSender {
    fun send(email: String, authKey: String)
}
