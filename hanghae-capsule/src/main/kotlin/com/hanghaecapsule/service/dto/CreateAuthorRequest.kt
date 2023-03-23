package com.hanghaecapsule.service.dto

import io.swagger.v3.oas.annotations.media.Schema


data class CreateAuthorRequest(
    @field:Schema(description = "작성자 이메일")
    val email: String
)
