package com.example.demoserver.controller

import com.example.demoserver.entity.MemberEntity
import com.example.demoserver.service.MemberService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "[Member] 사용자 관련 API")
class MemberController(
    private val memberService: MemberService
) {

    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "회원가입 성공"),
            ApiResponse(responseCode = "400", description = "회원가입 실패")
        ]
    )
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

    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "로그인 성공"),
            ApiResponse(responseCode = "400", description = "로그아웃 실패")
        ]
    )
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
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "회원가입 성공"),
            ApiResponse(responseCode = "404", description = "회원가입 실패")
        ]
    )
    @GetMapping("/{memberId}"
    )
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