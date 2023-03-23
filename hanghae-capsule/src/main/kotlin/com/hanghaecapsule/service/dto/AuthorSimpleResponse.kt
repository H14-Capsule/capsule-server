package com.hanghaecapsule.service.dto

import com.hanghaecapsule.domain.author.Author
import io.swagger.v3.oas.annotations.media.Schema

data class AuthorSimpleResponse(
    @field:Schema(description = "작성자 식별자 id")
    val id: Long,

    @field:Schema(description = "작성자 이메일")
    val email: String,
) {

    companion object {
        fun of(author: Author): AuthorSimpleResponse {
            return AuthorSimpleResponse(
                id = author.id,
                email = author.email
            )
        }
    }
}
