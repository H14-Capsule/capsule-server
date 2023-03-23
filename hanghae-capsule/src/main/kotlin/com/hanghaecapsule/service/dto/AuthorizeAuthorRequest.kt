package com.hanghaecapsule.service.dto

import io.swagger.v3.oas.annotations.media.Schema

data class AuthorizeAuthorRequest(
    @field:Schema(description = "작성자 식별자 id")
    val id: Long,

    @field:Schema(description = "작성자 인증키")
    val authKey: String,
)
