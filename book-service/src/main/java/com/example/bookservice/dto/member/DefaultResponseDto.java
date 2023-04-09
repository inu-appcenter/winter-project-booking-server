package com.example.bookservice.dto.member;

import lombok.Getter;
import lombok.Builder;


@Getter
@Builder
public class DefaultResponseDto {

    private Boolean success;

    private String msg;
}
