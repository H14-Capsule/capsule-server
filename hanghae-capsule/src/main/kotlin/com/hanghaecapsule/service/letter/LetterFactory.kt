package com.hanghaecapsule.service.letter

import com.hanghaecapsule.domain.author.Author
import com.hanghaecapsule.domain.letter.Letter
import com.hanghaecapsule.domain.letter.PDFLetter

fun interface LetterFactory {
    fun generate(author: Author, letter: Letter): PDFLetter
}