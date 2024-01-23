package com.example.demoserver.entity

import jakarta.persistence.*


@Entity
@Table(name = "letter")
class LetterEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val code: String,
    @Column(columnDefinition = "TEXT")
    val content: String
) {
}