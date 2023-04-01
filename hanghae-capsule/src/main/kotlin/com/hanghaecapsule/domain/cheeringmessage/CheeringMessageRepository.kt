package com.hanghaecapsule.domain.cheeringmessage

import org.springframework.data.jpa.repository.JpaRepository

interface CheeringMessageRepository : JpaRepository<CheeringMessage, Long> {
    fun findAllByOrderByIdDesc(): List<CheeringMessage>
}
