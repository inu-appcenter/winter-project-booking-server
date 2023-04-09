package com.example.bookservice.dto.lib;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LibInfoResponseDto {
    @ApiModelProperty(example = "국내서 단행본")
    @ApiParam(value = "자료유형")
    private String type;

    @ApiModelProperty(example = "bookImageURL")
    @ApiParam(value = "책 이미지")
    private String thumbnailUrl;

    @ApiModelProperty(example = "005가 394ㄱ")
    @ApiParam(value = "청구 기호")
    private String billNum;

    @ApiModelProperty(example = "제1자료실(3층)")
    @ApiParam(value = "소장 위치")
    private String location;

    @ApiModelProperty(example = "4")
    @ApiParam(value = "대출 가능 도서의 수")
    private int availableNum;

    @ApiModelProperty(example = "5")
    @ApiParam(value = "도서관에 있는 전체 도서의 수")
    private int entireNum;

    @ApiModelProperty(example = "lib.inu.ac.kr/bookName")
    @ApiParam(value = "도서관 검색 결과 url")
    private String bookUrl;
}
