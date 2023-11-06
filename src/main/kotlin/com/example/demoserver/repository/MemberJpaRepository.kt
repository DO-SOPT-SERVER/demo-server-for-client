package com.example.demoserver.repository

import com.example.demoserver.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {

    fun findByUsername(username: String): MemberEntity?

    fun existsByUsername(username: String): Boolean

}