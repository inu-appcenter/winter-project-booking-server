package com.example.bookservice.dto.member;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInResponseDto extends SignUpResponseDto{

    private String token;

    @Builder
    public SignInResponseDto(Boolean success, String msg, String token) {
        super(success, msg);
        this.token = token;
    }
}
