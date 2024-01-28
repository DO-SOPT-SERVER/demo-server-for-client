package com.example.demoserver.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class IndexController {

    @GetMapping("/doserver")
    fun index(): String {
        return "doserver"
    }
}