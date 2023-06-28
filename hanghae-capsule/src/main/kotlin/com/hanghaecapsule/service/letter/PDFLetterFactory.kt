package com.hanghaecapsule.service.letter

import com.hanghaecapsule.domain.author.Author
import com.hanghaecapsule.domain.letter.Letter
import com.hanghaecapsule.domain.letter.PDFLetter
import org.springframework.stereotype.Component

@Component
class PDFLetterFactory: LetterFactory {
    override fun generate(author: Author, letter: Letter): PDFLetter {
        TODO("PDF 편지를 만드는 기능 구현 예정")
    }
}