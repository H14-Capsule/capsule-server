package com.hanghaecapsule.controller.cheeringmessage

import com.hanghaecapsule.service.cheeringmessage.CheeringMessageCommandService
import com.hanghaecapsule.service.dto.SaveCheeringMessageRequest
import com.hanghaecapsule.service.dto.SaveCheeringMessageResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name = "응원글 관련 API")
@RestController
@RequestMapping("/api/v1/cheering-message")
class CheeringMessageController(
    private val cheeringMessageCommandService: CheeringMessageCommandService,
) {
    @PostMapping
    fun createCheeringMessage(
        @Valid @RequestBody request: SaveCheeringMessageRequest,
    ): ResponseEntity<SaveCheeringMessageResponse> {
        val response = cheeringMessageCommandService.createCheeringMessage(request)
        return ResponseEntity.ok(response)
    }
}
