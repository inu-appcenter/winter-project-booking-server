package com.example.bookservice.dto.member;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
public class SignInResponseDto extends SignUpResponseDto{

    private String token;
    public SignInResponseDto(Boolean success, String msg, String token) {
        super(success, msg);
        this.token = token;
    }
}
