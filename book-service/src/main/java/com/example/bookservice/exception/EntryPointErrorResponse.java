package com.example.bookservice.exception;

import lombok.Getter;
import lombok.Builder;


@Getter
@Builder
public class EntryPointErrorResponse {

    private String msg;
}
