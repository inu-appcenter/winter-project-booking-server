package com.example.bookservice.service;

import com.example.bookservice.domain.Book;
import com.example.bookservice.domain.Member;
import com.example.bookservice.exception.CustomException;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.bookservice.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    private final MemberRepository memberRepository;

    // 책 전체 조회
    @Transactional
    public List<Book> findAll() {
        List<Book> foundBooks = null;

        try
        {
            foundBooks = bookRepository.findAll();
        }
        catch (RuntimeException e)
        {
            throw new CustomException(SERVER_ERROR);
        }

        if (foundBooks.isEmpty())
        {
            throw new CustomException(BOOK_NOT_FOUND);
        }

        return foundBooks;
    }

    // 책 조회
    @Transactional
    public Book findById(Long bookId) {
        Book foundBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new CustomException(BOOK_NOT_FOUND));

        return foundBook;
    }
}
