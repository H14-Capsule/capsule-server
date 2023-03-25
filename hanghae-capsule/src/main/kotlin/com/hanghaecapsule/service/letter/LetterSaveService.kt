package com.hanghaecapsule.service.letter

import com.hanghaecapsule.domain.author.AuthorRepository
import com.hanghaecapsule.domain.author.findAuthor
import com.hanghaecapsule.domain.exception.UnAuthorizedException
import com.hanghaecapsule.domain.letter.LetterRepository
import com.hanghaecapsule.service.dto.CreateLetterRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class LetterSaveService(
    private val authorRepository: AuthorRepository,
    private val letterRepository: LetterRepository,
) {
    fun writeLetter(request: CreateLetterRequest): Long {
        val author = authorRepository.findAuthor(request.authorId)
        if (author.isNotAuthorized()) {
            throw UnAuthorizedException("인증받지 않은 작성자입니다. authorId = ${author.id}")
        }

        val letter = author.writeLetter(
            content = request.content,
        )

        letterRepository.save(letter)

        return letter.id
    }
}
