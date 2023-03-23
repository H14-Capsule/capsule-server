package com.hanghaecapsule.controller.letter

import com.hanghaecapsule.service.dto.CreateLetterRequest
import com.hanghaecapsule.service.letter.LetterSaveService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "편지 관련 API")
@RestController
@RequestMapping("/api/v1/letter")
class LetterController(
    private val letterSaveService: LetterSaveService,
) {
    @Operation(summary = "편지 작성")
    @PostMapping
    fun createLetter(
        @RequestBody request: CreateLetterRequest,
    ): ResponseEntity<Unit> {
        letterSaveService.writeLetter(request)

        return ResponseEntity.ok(Unit)
    }
}
