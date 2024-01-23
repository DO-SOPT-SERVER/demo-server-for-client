package com.example.demoserver.repository

import com.example.demoserver.entity.LetterEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LetterJpaRepository : JpaRepository<LetterEntity, Long> {
    fun findByCode(code: String): LetterEntity?
}