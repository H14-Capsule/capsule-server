package com.hanghaecapsule.service.dto

import com.hanghaecapsule.domain.author.Author

data class AuthorSimpleResponse(
    val id: Long,
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
