package com.hanghaecapsule.controller.letter

import com.hanghaecapsule.service.dto.CreateLetterRequest
import com.hanghaecapsule.service.letter.LetterSaveService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/letter")
class LetterController(
    private val letterSaveService: LetterSaveService,
) {
    @PostMapping
    fun createLetter(
        @RequestBody request: CreateLetterRequest,
    ): ResponseEntity<Unit> {
        letterSaveService.writeLetter(request)

        return ResponseEntity.ok(Unit)
    }
}
