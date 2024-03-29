package com.example.bookservice.dto.book;

import com.example.bookservice.domain.Book;
import com.example.bookservice.domain.BookType;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

@Getter
public class BookDto {

    @ApiModelProperty(example = "1")
    @ApiParam(value = "책 ID")
    private Long id;

    @ApiModelProperty(example = "1")
    @ApiParam(value = "과목의 ID")
    private Long subjectId;

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

    public BookDto(Book book) {
        this.id = book.getId();
        this.subjectId = book.getSubject().getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.year = book.getYear();
        this.bookType = book.getBookType();
    }
}
