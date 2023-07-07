package com.hanghaecapsule.service.letter

import com.hanghaecapsule.domain.author.Author
import com.hanghaecapsule.domain.letter.Letter
import com.hanghaecapsule.domain.letter.PDFLetter
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.common.PDRectangle.*
import org.apache.pdfbox.pdmodel.font.PDType0Font
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileInputStream
import java.util.*

@Component
class PDFLetterFactory : LetterFactory {
    @Suppress("UNREACHABLE_CODE")
    override fun generate(author: Author, letter: Letter): PDFLetter {
        val file = File("hanghae-capsule/src/main/resources/pdf/capsule-letter-background.pdf")
        val document = PDDocument.load(file)

        val fileName = UUID.randomUUID()
        decorateLetter(document, letter.content)
        document.save("${DIRECTORY_LOCATION}/${fileName}.pdf")

        document.close()
        return PDFLetter("${fileName}.pdf")
    }

    private fun decorateLetter(document: PDDocument, content: String) {

        val fontStream = FileInputStream(FONT_LOCATION)
        val font = PDType0Font.load(document, fontStream)

        //creating the PDPageContentStream object
        PDPageContentStream(document, document.getPage(0)).apply {
            //Drawing the image in the PDF document
            drawLetterTemplate(document)
            writeLetterContent(content, font)
            //Closing the PDPageContentStream object
            close()
        }
    }

    private fun PDPageContentStream.drawLetterTemplate(document: PDDocument) {
        val backgroundImg = PDImageXObject.createFromFile(
            "/Users/gimgyeonglog/IdeaProjects/private/hanghae-capsule/hanghae-capsule/src/main/resources/images/capsule-letter-background.png",
            document,
        )
        drawImage(backgroundImg, 0f, 0f)

        val logo = PDImageXObject.createFromFile(
            "/Users/gimgyeonglog/IdeaProjects/private/hanghae-capsule/hanghae-capsule/src/main/resources/images/hanghae-logo.png",
            document,
        )
        drawImage(
            logo, 550f, 1_200f,
            A4.width / 4,
            A4.width / 12,
        )
    }

    private fun PDPageContentStream.writeLetterContent(content: String, font: PDType0Font) {
        beginText()
        val fontSize = 24f
        setFont(font, fontSize)
        val width = A4.width / 5
        val startHeight = A2.height * 7 / 10
        newLineAtOffset(width, startHeight)

        val lineLimitLength = 30
        content.split("\n")
            .forEach {
                if (it.length < lineLimitLength) {
                    newLineAtOffset(0f, -(fontSize * 2))
                    showText(it)
                } else {
                    var currentContentLine = it
                    while (currentContentLine.isNotBlank() && currentContentLine.length > lineLimitLength) {
                        val targetLine = currentContentLine.substring(0, lineLimitLength)
                        currentContentLine = currentContentLine.substring(lineLimitLength)

                        newLineAtOffset(0f, -(fontSize * 2))
                        showText(targetLine)
                    }

                    newLineAtOffset(0f, -(fontSize * 2))
                    showText(currentContentLine)
                }
            }
        endText()
    }

    companion object {
        private const val DIRECTORY_LOCATION = "hanghae-capsule/src/main/resources/generate"

        private const val FONT_LOCATION =
            "{}/src/main/resources/fonts/KyoboHandwriting2022khn.ttf"
    }
}