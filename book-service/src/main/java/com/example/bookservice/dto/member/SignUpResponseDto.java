package com.example.bookservice.dto.member;

import lombok.Getter;
import lombok.Builder;


@Getter
@Builder
public class SignUpResponseDto {

    private Boolean success;

    private String msg;
}
