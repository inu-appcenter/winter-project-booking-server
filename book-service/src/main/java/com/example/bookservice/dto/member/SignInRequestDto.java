package com.example.bookservice.dto.member;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SignInRequestDto {

    @ApiModelProperty(example = "example")
    @ApiParam(value = "아이디")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId;

    @ApiModelProperty(example = "example")
    @ApiParam(value = "비밀번호")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
