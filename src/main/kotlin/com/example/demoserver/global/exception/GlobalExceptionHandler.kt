package com.example.demoserver.global.exception

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse(message = exception.message ?: "IllegalArgumentException"))
    }

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun handleEntityNotFoundException(exception: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse(message = exception.message ?: "EntityNotFoundException"))
    }

    data class ErrorResponse(
        val message: String
    )
}