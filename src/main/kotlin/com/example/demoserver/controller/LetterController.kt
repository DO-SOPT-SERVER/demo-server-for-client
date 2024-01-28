package com.example.demoserver.controller

import com.example.demoserver.controller.dto.response.LetterResponse
import com.example.demoserver.service.LetterFinder
import com.example.demoserver.service.dto.response.ServiceLetterResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class LetterController(
    val letterFinder: LetterFinder
) {
    @GetMapping("/letter")
    fun findLetter(@RequestParam code: String, model: Model): String {
        val serviceLetterResponse: ServiceLetterResponse = letterFinder.findLetterByCode(code)
        LetterResponse(
                id = serviceLetterResponse.id,
                name = serviceLetterResponse.name,
                content = serviceLetterResponse.content
            ).also {
                model.addAttribute("letter", it)
            }
        return "letter"
    }
}