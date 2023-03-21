package com.example.bookservice.controller;

import com.example.bookservice.domain.Member;
import com.example.bookservice.dto.member.*;
import com.example.bookservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/sign-up")
    public SignUpResponseDto signUp(@RequestBody @Validated SignUpRequestDto signUpRequestDto) {
        log.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}", signUpRequestDto.getEmail(), signUpRequestDto.getName());
        SignUpResponseDto signUpResponseDto = memberService.signUp(signUpRequestDto);
        log.info("[signUp] 회원가입을 완료했습니다. id : {}", signUpRequestDto.getEmail());

        return signUpResponseDto;
    }

    // 로그인
    @PostMapping("/sign-in")
    public SignInResponseDto signIn(@RequestBody @Validated SignInRequestDto signInRequestDto) {
        log.info("[signIn] 로그인을 수행합니다. id : {}, password : ****", signInRequestDto.getEmail());
        SignInResponseDto signInResponseDto = memberService.signIn(signInRequestDto);
        log.info("[signIn] 로그인 되었습니다. id : {}, token : {}", signInRequestDto.getEmail(), signInResponseDto.getToken());

        return signInResponseDto;
    }

    // 회원 조회
    @GetMapping
    public MemberResponse findById() {
        log.info("[findById] 회원 조회를 수행합니다.");
        Member member = getMember();
        log.info("[findById] 회원 조회를 완료했습니다. id : {}", getMember().getId());
        return new MemberResponse(member);
    }

    // 회원 삭제
    @DeleteMapping
    public ResponseResult delete() {
        log.info("[delete] 회원 탈퇴를 수행합니다.");
        Member member = getMember();
        memberService.delete(member.getId());
        log.info("[delete] 회원 탈퇴를 완료했습니다. id : {}", member.getId());
        return new ResponseResult(true, "회원 탈퇴 완료");
    }

    private static Member getMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  (Member)authentication.getPrincipal();
    }
}
