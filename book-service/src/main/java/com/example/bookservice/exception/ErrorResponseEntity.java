package com.example.bookservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponseEntity {
    private int status;
    private String code;
    private String msg;

    public ErrorResponseEntity(ErrorCode errorCode)
    {
        this.status = errorCode.getStatus().value();
        this.code = errorCode.name();
        this.msg = errorCode.getMsg();
    }

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e)
    {
        return ResponseEntity
                .status(e.getStatus())
                .body(ErrorResponseEntity.builder()
                        .status(e.getStatus().value())
                        .code(e.name())
                        .msg(e.getMsg())
                        .build()
                );
    }

}
