package com.hanghaecapsule.service.cheeringmessage

import com.hanghaecapsule.domain.cheeringmessage.CheeringMessage
import com.hanghaecapsule.domain.cheeringmessage.CheeringMessageRepository
import com.hanghaecapsule.service.dto.SaveCheeringMessageRequest
import com.hanghaecapsule.service.dto.SaveCheeringMessageResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CheeringMessageCommandService(
    private val cheeringMessageRepository: CheeringMessageRepository,
) {
    fun createCheeringMessage(request: SaveCheeringMessageRequest): SaveCheeringMessageResponse {
        val cheeringMessage = cheeringMessageRepository.save(
            CheeringMessage(
                request.nickname,
                request.content,
            )
        )

        return SaveCheeringMessageResponse.of(cheeringMessage)
    }
}
