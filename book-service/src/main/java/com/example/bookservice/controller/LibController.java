package com.example.bookservice.controller;

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

@Api(tags = "도서관 도서 검색 API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lib")
@RequiredArgsConstructor
@Slf4j
public class LibController {

    @GetMapping("/textbooks")
    @ApiOperation(value = "도서관 책 조회", notes = "전공책 이름으로 도서관 도서 검색을 실시합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "도서관 책 조회 성공"),
            @ApiResponse(code = 404, message = "해당하는 책이 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public void findTextbook() {
        log.info("[findTextbook] 도서관 도서 검색을 진행합니다");
    }

    @GetMapping("/status")
    @ApiOperation(value = "도서관 책 상태 조회", notes = "전공책의 대출현황(상태) 정보를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "도서관 책 대출현황 조회 성공"),
            @ApiResponse(code = 404, message = "해당하는 책이 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public void findTextbookStatus() {
        log.info("[findTextbookStatus] 도서관 도서의 현황 검색을 진행합니다");
    }

    @GetMapping("/images")
    @ApiOperation(value = "도서관 책 표지 조회", notes = "전공책의 표지 링크를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "도서관 책 표지 조회 성공"),
            @ApiResponse(code = 404, message = "해당하는 표지 이미지가 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public void findTextbookImg() {
        log.info("[findTextbookImg 도서관 도서 표지 검색을 진행합니다]");
    }
}
