package com.example.bookservice.controller;

import com.example.bookservice.dto.lib.LibInfoResponseDto;
import com.example.bookservice.service.LibService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Api(tags = "도서관 도서 검색 API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lib")
@RequiredArgsConstructor
@Slf4j
public class LibController {

    private final LibService libService;

    @GetMapping("/info")
    @ApiOperation(value = "도서관 책 조회", notes = "전공책 이름으로 도서관 도서 검색을 실시합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "도서관 책 조회 성공"),
            @ApiResponse(code = 404, message = "해당하는 책이 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public LibInfoResponseDto findTextBookInfo(String bookName) throws UnsupportedEncodingException, JsonProcessingException {
        log.info("[findTextBookInfo] 도서관 도서 검색을 진행합니다");
        LibInfoResponseDto libInfoResponseDto = libService.findInfo(bookName);
        return libInfoResponseDto;
    }
}
