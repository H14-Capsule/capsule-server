package com.hanghaecapsule.domain.cheeringmessage

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class CheeringMessage(
    @Column(name = "nickname", nullable = false)
    val nickname: String,

    @Lob
    @Column(name = "content", nullable = false)
    val content: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
)
