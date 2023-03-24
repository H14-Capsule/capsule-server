package com.hanghaecapsule.service.author

import com.hanghaecapsule.common.AuthKeyGenerator
import com.hanghaecapsule.domain.author.Author
import com.hanghaecapsule.domain.author.AuthorRepository
import com.hanghaecapsule.domain.author.findAuthor
import com.hanghaecapsule.service.dto.AuthorSimpleResponse
import com.hanghaecapsule.service.dto.AuthorizeAuthorRequest
import com.hanghaecapsule.service.dto.CreateAuthorRequest
import com.hanghaecapsule.service.external.EmailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class AuthorCommandService(
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

        // TODO 2023-03-24 경록: 비동기로 동작하도록 변경 예정
        emailSender.send(request.email, authKey)

        return AuthorSimpleResponse.of(author)
    }

    fun authorize(request: AuthorizeAuthorRequest): Boolean {
        val author = authorRepository.findAuthor(request.id)
        val currentTime = LocalDateTime.now()
        return author.authorize(request.authKey, currentTime)
    }

    fun reIssueAuthKey(authorId: Long) {
        val author = authorRepository.findAuthor(authorId)
        val newAuthKey = AuthKeyGenerator.generate()
        author.reIssueAuthKey(newAuthKey)

        emailSender.send(author.email, newAuthKey)
    }
}
