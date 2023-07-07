package com.hanghaecapsule.service.external

import com.hanghaecapsule.domain.letter.PDFLetter
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.io.File

@Component
class EmailSenderClient(
    private val javaMailSender: JavaMailSender,

    @Value("#{hanghae-capsule.mail.email}")
    private val hanghaeEmail: String
): EmailSender {

    override fun send(email: String, authKey: String) {
        javaMailSender.send(
            SimpleMailMessage().apply {
                setTo(email)
                subject = "[항해캡슐] 이메일 인증 안내"
                text = "인증번호: $authKey"
            }
        )
    }

    override fun sendLetter(capsuleLetter: PDFLetter, email: String) {
        val mimeMessage = javaMailSender.createMimeMessage()

        // use multipart (true)
        val rootPath = "파일 저장할 루트경로"
        val file = File("${rootPath}/${capsuleLetter.url}")
        MimeMessageHelper(mimeMessage, true, "UTF-8")
            .apply {
                setSubject("항해99를 끝마친 나에게...")
                setFrom(hanghaeEmail)
                setTo(email)
                setText("""
                    안녕하세요. 항해99 14기 최유리입니다. 
                    본과정 시작할때 써주셨던 편지를 열어보는 날입니다. 
                    지금까지 항해를 달려온 여러분들 다들 고생 많으셨습니다~! 
                    항해는 끝났지만 앞으로 남은 취업 정말 다들 좋은 곳 갈 수 있도록 기원할게요! 
                    다시한번 고생 많으셨고 응원하겠습니다.~!
                """.trimIndent())
                addAttachment("항해캡슐", file)
            }

        javaMailSender.send(mimeMessage)
    }
}
