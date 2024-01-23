package com.example.demoserver.service

import com.example.demoserver.entity.LetterEntity
import com.example.demoserver.repository.LetterJpaRepository
import com.example.demoserver.service.dto.response.ServiceLetterResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class LetterFinder(
    private val letterRepository: LetterJpaRepository
) {

    @Transactional(readOnly = true)
    fun findLetterByCode(code: String) : ServiceLetterResponse {
        val letter: LetterEntity = letterRepository.findByCode(code)
            ?: throw IllegalArgumentException("해당 편지가 존재하지 않습니다.")
        return ServiceLetterResponse(
            id = letter.id,
            name = letter.name,
            content = letter.content
        )
    }
}