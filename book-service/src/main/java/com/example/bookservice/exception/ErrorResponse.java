package com.example.bookservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final HttpStatus status;
    private final int code;
    private final String msg;

    public ErrorResponse(ErrorCode errorCode)
    {
        this.status = errorCode.getStatus();
        this.code = errorCode.getStatus().value();
        this.msg = errorCode.getMsg();
    }

    public ResponseEntity<ErrorResponse> error(CustomException e)
    {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .status(e.getErrorCode().getStatus())
                        .code(e.getErrorCode().getStatus().value())
                        .msg(e.getErrorCode().getMsg())
                        .build());
    }

}
