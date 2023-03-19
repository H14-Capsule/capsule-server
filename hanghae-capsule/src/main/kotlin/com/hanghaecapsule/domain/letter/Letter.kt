package com.hanghaecapsule.domain.letter

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob

@Entity(name = "letter")
class Letter(
    @Lob
    @Column(name = "content")
    val content: String,

    @Column(name = "auth_id")
    val authId: Long,

    @Column(name = "send_date")
    val sendDate: LocalDate,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
)
