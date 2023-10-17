package com.example.demoserver.controller

import com.example.demoserver.entity.MemberEntity
import com.example.demoserver.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping
    fun signUp(@RequestBody request: MemberCreateRequest): ResponseEntity<Unit> {
        val location: URI = URI.create("api/v1/members/" + memberService.create(request))
        return ResponseEntity.created(location).build()
    }

    data class MemberCreateRequest(
        val username: String,
        val password: String,
        val nickname: String
    ) {

    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: MemberSignInRequest): ResponseEntity<Unit> {
        memberService.signIn(request)
        return ResponseEntity.noContent().build()
    }

    data class MemberSignInRequest(
        val username: String,
        val password: String
    ) {

    }

    @GetMapping("/{memberId}")
    fun getMember(@PathVariable memberId: Long ): ResponseEntity<MemberGetResponse> {
        val response: MemberGetResponse = memberService.getById(memberId)
        return ResponseEntity.ok(response)
    }

    data class MemberGetResponse(
        val username: String,
        val nickname: String
    ) {
        companion object {
            fun of(member: MemberEntity): MemberGetResponse {
                return MemberGetResponse(
                    username = member.username,
                    nickname = member.nickname
                )
            }
        }
    }
}