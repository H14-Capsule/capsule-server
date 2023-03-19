package com.hanghaecapsule.service.external

import com.hanghaecapsule.service.external.EmailSender
import org.springframework.stereotype.Component

@Component
class EmailSenderClient: EmailSender {
    override fun send(email: String, authKey: String) {
        // TODO 2023-03-19 경록: 추후 이메일 발송 로직 구현 예정
    }
}
