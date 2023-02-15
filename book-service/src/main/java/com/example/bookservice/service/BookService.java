package com.example.bookservice.service;

import com.example.bookservice.domain.Book;
import com.example.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    // 책 전체 조회
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // 책 조회
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException());
    }
}
