package com.hanghaecapsule.service.dto

import com.hanghaecapsule.domain.cheeringmessage.CheeringMessage
import io.swagger.v3.oas.annotations.media.Schema

data class SaveCheeringMessageResponse(
    @field:Schema(description = "응원글 식별자 id")
    val id: Long,

    @field:Schema(description = "응원글 닉네임")
    val nickname: String,

    @field:Schema(description = "응원글 콘텐츠 내용")
    val content: String,
) {
    companion object {
        fun of(cheeringMessage: CheeringMessage): SaveCheeringMessageResponse {
            return SaveCheeringMessageResponse(
                id =  cheeringMessage.id,
                nickname = cheeringMessage.nickname,
                content = cheeringMessage.content,
            )
        }
    }
}
