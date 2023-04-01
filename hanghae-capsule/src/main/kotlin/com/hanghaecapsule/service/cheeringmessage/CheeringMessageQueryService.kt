package com.hanghaecapsule.service.cheeringmessage

import com.hanghaecapsule.domain.cheeringmessage.CheeringMessageRepository
import com.hanghaecapsule.service.dto.SimpleCheeringMessageResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CheeringMessageQueryService(
    private val cheeringMessageRepository: CheeringMessageRepository,
) {
    fun queryAllCheeringMessage(): List<SimpleCheeringMessageResponse> {
        return cheeringMessageRepository.findAllByOrderByIdDesc()
            .map { SimpleCheeringMessageResponse.of(it) }
    }
}
