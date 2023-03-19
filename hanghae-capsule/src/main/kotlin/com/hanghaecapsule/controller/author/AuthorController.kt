package com.hanghaecapsule.controller.author

import com.hanghaecapsule.service.author.AuthorSaveService
import com.hanghaecapsule.service.dto.AuthorSimpleResponse
import com.hanghaecapsule.service.dto.AuthorizeAuthorRequest
import com.hanghaecapsule.service.dto.CreateAuthorRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/author")
class AuthorController(
    private val authorSaveService: AuthorSaveService,
) {
    @PostMapping
    fun createAuthor(
        @RequestBody request: CreateAuthorRequest,
    ): ResponseEntity<AuthorSimpleResponse> {
        val response = authorSaveService.createAuthor(request)

        return ResponseEntity.ok(response)
    }

    @PutMapping("/authorize")
    fun authorizeAuthKey(
        @RequestBody request: AuthorizeAuthorRequest,
    ): ResponseEntity<Boolean> {
        val result = authorSaveService.authorize(request)
        return ResponseEntity.ok(result)
    }
}
