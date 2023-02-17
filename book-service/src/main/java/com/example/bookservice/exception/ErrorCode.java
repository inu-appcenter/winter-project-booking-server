package com.example.bookservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // == 200 == //
    SUCCESS(HttpStatus.OK, "OK"),

    // == 4xx == //
    SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 과목이 존재하지 않습니다"),
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 책이 존재하지 않습니다"),

    // == 500 == //
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 에러가 발생하였습니다");

    private final HttpStatus status;
    private final String msg;

}
