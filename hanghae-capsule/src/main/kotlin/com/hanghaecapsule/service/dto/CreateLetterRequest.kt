package com.hanghaecapsule.service.dto

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class CreateLetterRequest(
    @field:Schema(description = "작성자 식별자 id")
    @field: Positive(message = "작성자 식별자 값은 양수만 허용합니다.")
    val authorId: Long,

    @field:Schema(description = "편지 내용")
    @field: NotBlank(message = "편지 내용은 공백을 허용하지 않습니다.")
    val content: String,
)
