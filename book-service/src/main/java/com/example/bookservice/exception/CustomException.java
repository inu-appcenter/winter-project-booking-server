package com.example.bookservice.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorcode)
    {
        this.errorCode = errorcode;
    }
}
