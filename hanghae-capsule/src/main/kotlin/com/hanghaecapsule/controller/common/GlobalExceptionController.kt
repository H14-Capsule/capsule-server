package com.hanghaecapsule.controller.common

import com.hanghaecapsule.domain.exception.UnAuthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 전역 예외 핸들러 대략적으로 구현
 */
@RestControllerAdvice
class GlobalExceptionController {

    @ExceptionHandler(UnAuthorizedException::class)
    fun handleUnAuthorizedException(e: UnAuthorizedException): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleUnAuthorizedException(e: RuntimeException): ResponseEntity<Unit> {
        return ResponseEntity.badRequest().build()
    }
}
