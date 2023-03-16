package com.example.bookservice.dto.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Email(message = "올바르지 않은 이메일 형식입니다.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
