package com.example.demoserver.entity

import jakarta.persistence.*


@Entity
@Table(name = "member")
class MemberEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String,
    val password: String,
    val nickname: String,
) {
}