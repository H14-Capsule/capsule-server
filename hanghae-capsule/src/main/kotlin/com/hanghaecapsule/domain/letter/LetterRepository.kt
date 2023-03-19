package com.hanghaecapsule.domain.letter

import org.springframework.data.jpa.repository.JpaRepository

interface LetterRepository : JpaRepository<Letter, Long>
