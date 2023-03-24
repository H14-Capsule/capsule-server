package com.hanghaecapsule.service.external

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class EmailSenderClient(
    private val javaMailSender: JavaMailSender,
): EmailSender {
    override fun send(email: String, authKey: String) {
        // TODO 2023-03-19 경록: 추후 이메일 발송 로직 구현 예정

        javaMailSender.send(
            SimpleMailMessage().apply {
                setTo(email)
                subject = "[항해캡슐] 이메일 인증 안내"
                text = "인증번호: $authKey"
            }
        )
    }
}
