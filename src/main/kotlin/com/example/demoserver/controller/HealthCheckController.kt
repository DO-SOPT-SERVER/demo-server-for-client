package com.example.demoserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/health")
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