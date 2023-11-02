package com.example.demoserver.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/health")
@Tag(name = "서버 Health Check API", description = "항상 OK를 반환합니다.")
class HealthCheckController {


        @GetMapping
        fun healthCheck(): HealthCheckResponse {
            return HealthCheckResponse("OK")
        }

    data class HealthCheckResponse(
        val status: String
    ) {

    }
}