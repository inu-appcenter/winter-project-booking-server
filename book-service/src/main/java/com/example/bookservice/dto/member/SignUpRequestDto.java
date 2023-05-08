package com.example.bookservice.dto.member;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SignUpRequestDto {

    @ApiModelProperty(example = "이름")
    @ApiParam(value = "이주원")
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @ApiModelProperty(example = "단과대")
    @ApiParam(value = "정보기술대학")
    @NotBlank(message = "단과대를 입력해주세요.")
    private String college;

    @ApiModelProperty(example = "학과")
    @ApiParam(value = "컴퓨터공학부")
    @NotBlank(message = "학과를 입력해주세요.")
    private String department;


    @ApiModelProperty(example = "example")
    @ApiParam(value = "아이디")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId;

    @ApiModelProperty(example = "example")
    @ApiParam(value = "비밀번호")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
