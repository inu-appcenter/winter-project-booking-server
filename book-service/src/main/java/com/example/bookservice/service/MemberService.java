package com.example.bookservice.service;

import com.example.bookservice.config.security.JwtTokenProvider;
import com.example.bookservice.domain.Member;
import com.example.bookservice.dto.member.*;
import com.example.bookservice.exception.CustomException;
import com.example.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.example.bookservice.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        log.info("[아이디 중복 검사]");
        if (memberRepository.existsByLoginId(signUpRequestDto.getLoginId())) {
            throw new CustomException(DUPLICATE_MEMBER_ID);
        }
        log.info("[아이디 중복 검사 통과]");

        Member member = Member.builder()
                .loginId(signUpRequestDto.getLoginId())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .name(signUpRequestDto.getName())
                .college(signUpRequestDto.getCollege())
                .department(signUpRequestDto.getDepartment())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        memberRepository.save(member);
        SignUpResponseDto signUpResponseDto = SignUpResponseDto.builder()
                .msg("회원가입 성공")
                .success(true)
                .build();

        log.info("[SignUp] 회원가입 성공");
        return signUpResponseDto;
    }

    // 로그인
    @Transactional
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        log.info("[signInRequestDto] 아이디 비교 수행");
        Member member = memberRepository.getByLoginId(signInRequestDto.getLoginId())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        log.info("[signInRequestDto] 아이디 비교 수행 완료");

        log.info("[signInRequestDto] 패스워드 비교 수행");
        if (!passwordEncoder.matches(signInRequestDto.getPassword(), member.getPassword())) {
            throw new CustomException(MEMBER_NOT_FOUND);
        }
        log.info("[signInRequestDto] 패스워드 비교 수행 완료");


        SignUpResponseDto signUpResponseDto = SignUpResponseDto.builder()
                .success(true)
                .msg("로그인 성공")
                .build();

        SignInResponseDto signInResponseDto = new SignInResponseDto(signUpResponseDto.getSuccess(), signUpResponseDto.getMsg(), jwtTokenProvider.createToken(signInRequestDto.getLoginId(), member.getRoles()));


        return signInResponseDto;
    }

    // 회원 조회
    @Transactional
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }

    // 회원 탈퇴
    @Transactional
    public void delete(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        memberRepository.deleteById(memberId);
    }
}
