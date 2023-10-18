package com.example.demoserver.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "member")
class MemberEntity(
    @Id @GeneratedValue
    val id: Long? = null,
    val username: String,
    val password: String,
    val nickname: String,
) {
}