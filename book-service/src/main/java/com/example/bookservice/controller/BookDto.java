package com.example.bookservice.controller;

import com.example.bookservice.domain.Book;
import com.example.bookservice.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookDto {

    private Long id;

    private String title;

    private String author;

    private String publisher;

    private int year;

    private String type;

    private Boolean isSaved;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.year = book.getYear();
        this.type = book.getType();
        this.isSaved = book.getIsSaved();
    }
}
