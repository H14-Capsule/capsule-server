package com.hanghaecapsule.domain.author

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

fun AuthorRepository.findAuthor(id: Long): Author =
    findByIdOrNull(id) ?: throw IllegalArgumentException("존재하지 않는 author입니다. id = $id")

interface AuthorRepository : JpaRepository<Author, Long>
