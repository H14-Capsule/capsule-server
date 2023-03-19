package com.hanghaecapsule.service.author

import com.hanghaecapsule.common.AuthKeyGenerator
import com.hanghaecapsule.domain.author.Author
import com.hanghaecapsule.domain.author.AuthorRepository
import com.hanghaecapsule.service.dto.AuthorSimpleResponse
import com.hanghaecapsule.service.dto.CreateAuthorRequest
import com.hanghaecapsule.service.external.EmailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class AuthorSaveService(
    private val authorRepository: AuthorRepository,
    private val emailSender: EmailSender,
) {
    fun createAuthor(request: CreateAuthorRequest): AuthorSimpleResponse {
        val authKey = AuthKeyGenerator.generate()
        val author = authorRepository.save(
            Author(
                authKey = authKey,
                lastIssueAuthKeyAt = LocalDateTime.now(),
                email = request.email
            )
        )

        emailSender.send(request.email, authKey)

        return AuthorSimpleResponse.of(author)
    }
}
