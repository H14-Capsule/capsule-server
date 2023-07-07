package com.hanghaecapsule.domain.letter

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "letter")
class Letter(
    @Lob
    @Column(name = "content")
    val content: String,

    @Column(name = "author_id")
    val authorId: Long,

    @Column(name = "send_date")
    val sendDate: LocalDate = LocalDate.of(2023, 7, 7), // 항해 14기 수료일

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Enumerated(value = EnumType.STRING)
    @Column(name = "send_status", nullable = false)
    var sendStatus: SendStatus = SendStatus.WAIT,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) {
    fun doneSend() {
        sendStatus = SendStatus.DONE
    }
}
