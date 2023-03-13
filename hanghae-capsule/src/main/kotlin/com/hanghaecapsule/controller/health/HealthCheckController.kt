package com.hanghaecapsule.controller.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/health")
    fun heathCheck(): String {
        return "OK"
    }
}
