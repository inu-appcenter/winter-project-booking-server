package com.example.bookservice.dto.member;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SignInRequestDto {

    @ApiModelProperty(example = "example@inu.ac.kr")
    @ApiParam(value = "이메일 - 아이디로 변경 예정")
    @NotBlank(message = "아이디를 입력해주세요.")
    @Email(message = "올바르지 않은 이메일 형식입니다.")
    private String email;

    @ApiModelProperty(example = "example")
    @ApiParam(value = "비밀번호")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
