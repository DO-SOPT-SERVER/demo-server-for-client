package com.example.demoserver.controller

import com.example.demoserver.entity.MemberEntity
import com.example.demoserver.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "[Member] 사용자 관련 API")
class MemberController(
    private val memberService: MemberService
) {


    @Operation(summary = "사용자 이름 중복 체크 API", description = "query string으로 username을 받아서 중복 여부를 반환합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "중복 여부에 관계 없이 200을 반환합니다."),
        ]
    )
    @GetMapping("/check")
    fun existsByUsername(@RequestParam username: String): ResponseEntity<CheckUsernameResponse> {
        val response: CheckUsernameResponse = CheckUsernameResponse(isExist = memberService.checkExistUsername(username))
        return ResponseEntity.ok(response)
    }

    data class CheckUsernameResponse(
        @Schema(example = "true", description = "true: 사용자가 존재함. false: 사용자가 존재하지 않음.")
        val isExist: Boolean
    ) {

    }



    @Operation(summary = "회원가입 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "회원가입 성공", content = [Content(schema = Schema(implementation = Unit::class))]),
            ApiResponse(responseCode = "400", description = "회원가입 실패", content = [Content(schema = Schema(implementation = Unit::class))])
        ]
    )
    @PostMapping
    fun signUp(@RequestBody request: MemberCreateRequest): ResponseEntity<Unit> {
        val location: URI = URI.create("api/v1/members/" + memberService.create(request))
        return ResponseEntity.created(location).build()
    }


    data class MemberCreateRequest(
        @Schema(example = "unanchoi")
        val username: String,
        @Schema(example = "1234", description = "Database에는 암호화된 비밀번호가 저장됩니다.")
        val password: String,
        @Schema(example = "윤한이가짱")
        val nickname: String
    ) {

    }

    @Operation(summary = "로그인 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "로그인 성공"),
            ApiResponse(responseCode = "400", description = "로그아웃 실패", content = [Content(schema = Schema(implementation = Nothing::class))])
        ]
    )
    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: MemberSignInRequest): ResponseEntity<MemberService.MemberSignInResponse> {
        val response:MemberService.MemberSignInResponse = memberService.signIn(request)
        return ResponseEntity.ok(response)
    }

    data class MemberSignInRequest(
        @Schema(example = "unanchoi")
        val username: String,
        @Schema(example = "1234")
        val password: String
    ) {

    }

    @Operation(summary = "회원 정보 조회 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "회원가입 성공"),
            ApiResponse(responseCode = "404", description = "회원가입 실패" , content = [Content(schema = Schema(implementation = Nothing::class))])
        ]
    )
    @GetMapping("/{memberId}"
    )
    fun getMember(@PathVariable memberId: Long ): ResponseEntity<MemberGetResponse> {
        val response: MemberGetResponse = memberService.getById(memberId)
        return ResponseEntity.ok(response)
    }

    data class MemberGetResponse(
        @Schema(example = "unanchoi")
        val username: String,
        @Schema(example = "윤한이가짱")
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