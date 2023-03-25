package com.hanghaecapsule.service.dto

import javax.validation.constraints.NotBlank

data class SaveCheeringMessageRequest(
    @field:NotBlank(message = "닉네임을 공백을 허용하지 않습니다.")
    val nickname: String,

    @field:NotBlank(message = "응원글은 공백을 허용하지 않습니다.")
    val content: String,
)
