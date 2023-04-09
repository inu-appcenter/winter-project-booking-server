package com.example.bookservice.dto.savebook;

import com.example.bookservice.domain.BookType;
import com.example.bookservice.domain.SaveBook;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

@Getter
public class SaveBookResponseDto {

    @ApiModelProperty(example = "1")
    @ApiParam(value = "책 ID")
    private Long id;

    @ApiModelProperty(example = "가깝고 먼 이야기, 색: 다채롭고 신비한 예술")
    @ApiParam(value = "책 제목")
    private String title;

    @ApiModelProperty(example = "한혜진")
    @ApiParam(value = "저자")
    private String author;

    @ApiModelProperty(example = "미진사")
    @ApiParam(value = "출판사")
    private String publisher;

    @ApiModelProperty(example = "2021")
    @ApiParam(value = "출판년도")
    private int year;

    @ApiModelProperty(example = "주교재 | 참고서적")
    @ApiParam(value = "책 유형")
    private BookType bookType;

    public SaveBookResponseDto(SaveBook saveBook) {
        this.id = saveBook.getId();
        this.title = saveBook.getTitle();
        this.author = saveBook.getAuthor();
        this.publisher = saveBook.getPublisher();
        this.year = saveBook.getYear();
        this.bookType = saveBook.getBookType();
    }
}
