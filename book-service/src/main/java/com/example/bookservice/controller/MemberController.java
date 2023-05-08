package com.example.bookservice.controller;

import com.example.bookservice.domain.Member;
import com.example.bookservice.dto.member.*;
import com.example.bookservice.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "회원 관리")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/sign-up")
    @ApiOperation(value = "회원가입", notes = "회원가입을 진행합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 완료"),
            @ApiResponse(code = 409, message = "회원 아이디가 중복되었습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public SignUpResponseDto signUp(@RequestBody @Validated SignUpRequestDto signUpRequestDto) {
        log.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}", signUpRequestDto.getLoginId(), signUpRequestDto.getName());
        SignUpResponseDto signUpResponseDto = memberService.signUp(signUpRequestDto);
        log.info("[signUp] 회원가입을 완료했습니다. id : {}", signUpRequestDto.getLoginId());

        return signUpResponseDto;
    }

    // 로그인
    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인", notes = "회원의 로그인을 진행합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 완료"),
            @ApiResponse(code = 400, message = "잘못된 형식을 입력하였습니다"),
            @ApiResponse(code = 404, message = "존재하지 않는 아이디입니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public SignInResponseDto signIn(@RequestBody @Validated SignInRequestDto signInRequestDto) {
        log.info("[signIn] 로그인을 수행합니다. id : {}, password : ****", signInRequestDto.getLoginId());
        SignInResponseDto signInResponseDto = memberService.signIn(signInRequestDto);
        log.info("[signIn] 로그인 되었습니다. id : {}, token : {}", signInRequestDto.getLoginId(), signInResponseDto.getToken());

        return signInResponseDto;
    }

    // 회원 조회
    @GetMapping
    @ApiOperation(value = "회원 조회", notes = "회원의 정보를 조회합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 정보 조회 성공"),
            @ApiResponse(code = 401, message = "인증이 실패하였습니다"),
            @ApiResponse(code = 404, message = "존재하지 않는 회원입니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public MemberResponseDto findById() {
        log.info("[findById] 회원 조회를 수행합니다.");
        Member member = memberService.findById(getMember().getId());
        log.info("[findById] 회원 조회를 완료했습니다. id : {}", getMember().getId());
        return new MemberResponseDto(member);
    }

    // 회원 삭제
    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴", notes = "회원의 정보를 삭제합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 탈퇴 성공"),
            @ApiResponse(code = 401, message = "인증이 실패하였습니다"),
            @ApiResponse(code = 404, message = "존재하지 않는 회원입니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public DefaultResponseDto delete() {
        log.info("[delete] 회원 탈퇴를 수행합니다.");
        Member member = getMember();
        memberService.delete(member.getId());
        log.info("[delete] 회원 탈퇴를 완료했습니다. id : {}", member.getId());
        return DefaultResponseDto.builder()
                .success(true)
                .msg("회원 탈퇴 완료")
                .build();
    }

    private static Member getMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Member) authentication.getPrincipal();
    }
}
