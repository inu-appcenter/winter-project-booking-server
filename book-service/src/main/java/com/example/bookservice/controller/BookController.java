package com.example.bookservice.controller;

import com.example.bookservice.domain.Book;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    // 책 전체 조회
    @GetMapping
    public List<BookDto> findAll() {
        log.info("[findAll] 책 전체 조회를 합니다.");
        List<Book> books = bookService.findAll();
        return books.stream()
                .map(book -> new BookDto(book))
                .collect(Collectors.toList());
    }

    // 책 조회
    @GetMapping("/{bookId}")
    public BookDto findById(@PathVariable Long bookId) {
        log.info("[findById] 책 조회를 합니다. id : {}", bookId);
        Book book = bookService.findById(bookId);
        return new BookDto(book);
    }
}
