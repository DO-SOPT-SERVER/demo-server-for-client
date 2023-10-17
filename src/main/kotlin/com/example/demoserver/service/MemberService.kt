package com.example.demoserver.service

import com.example.demoserver.controller.MemberController
import com.example.demoserver.controller.MemberController.MemberGetResponse
import com.example.demoserver.entity.MemberEntity
import com.example.demoserver.repository.MemberJpaRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberJpaRepository: MemberJpaRepository,
    private val encoder: PasswordEncoder
) {

    fun getById(id: Long) : MemberGetResponse {
        val member: MemberEntity = memberJpaRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("사용자가 존재하지 않습니다.") }
        return MemberController.MemberGetResponse.of(member)
    }

    @Transactional
    fun create(request: MemberController.MemberCreateRequest) : String {
        if (memberJpaRepository.existsByUsername(request.username)) {
            throw IllegalArgumentException("이미 존재하는 사용자입니다.")
        }

        val member: MemberEntity = MemberEntity(
            username = request.username,
            nickname = request.nickname,
            password = encoder.encode(request.password)
        )

        val savedMember = memberJpaRepository.save(member)
        return savedMember.id.toString()
    }

    fun signIn(request: MemberController.MemberSignInRequest) {
        val member: MemberEntity = memberJpaRepository.findByUsername(request.username)
            ?: throw IllegalArgumentException("사용자가 존재하지 않습니다.")
        if (!encoder.matches(request.password, member.password)) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        }
    }

    data class MemberSignInResponse(
        val username: String,
        val nickname: String
    ) {

    }

}