package com.example.demoserver.controller

import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/pepero")
class PeperoController(
    private val peperoService: PeperoService
) {

    @GetMapping
    fun pepero(): String {
        return "pepero"
    }

    @PostMapping("/result")
    fun getRandomOne(model: Model) : String {
        model.addAttribute("result", peperoService.getRandomOne())
        return "pepero_result"
    }

    @GetMapping("/result")
    fun peperoResult(): String{
        return "pepero_result"
    }

}

@Service
class PeperoService() {
        fun getRandomOne() : String {
            return serverPart.get((0..serverPart.size).random())
        }

    companion object {
        private val serverPart: List<String> = listOf("김성은","소예원","임주민","김수현","이동섭","도소현","정홍준","안현주","최승준","박예준","정준서","최승빈","최영린","황선웅","김태욱","김다현","현예진","윤정원","이혜연","석미혜","김승환","이승연","현예진","신민철","남궁찬","이나경","송민규","박재연","김보람","박상준","박지영","박희정","박경린","송하연")
    }
}