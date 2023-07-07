package com.hanghaecapsule.controller.cheeringmessage

import com.hanghaecapsule.service.cheeringmessage.CheeringMessageCommandService
import com.hanghaecapsule.service.cheeringmessage.CheeringMessageQueryService
import com.hanghaecapsule.service.dto.SaveCheeringMessageRequest
import com.hanghaecapsule.service.dto.SaveCheeringMessageResponse
import com.hanghaecapsule.service.dto.SimpleCheeringMessageResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Tag(name = "응원글 관련 API")
@RestController
@RequestMapping("/api/v1/cheering-message")
class CheeringMessageController(
    private val cheeringMessageCommandService: CheeringMessageCommandService,
    private val cheeringMessageQueryService: CheeringMessageQueryService,
) {
    @Operation(summary = "응원글 작성")
    @PostMapping
    fun createCheeringMessage(
        @Valid @RequestBody request: SaveCheeringMessageRequest,
    ): ResponseEntity<SaveCheeringMessageResponse> {
        val response = cheeringMessageCommandService.createCheeringMessage(request)
        return ResponseEntity.ok(response)
    }

    @Operation(summary = "모든 응원글 일괄 조회")
    @GetMapping
    fun getAll(): ResponseEntity<List<SimpleCheeringMessageResponse>> {
        val result = cheeringMessageQueryService.queryAllCheeringMessage()
        return ResponseEntity.ok(result)
    }
}
