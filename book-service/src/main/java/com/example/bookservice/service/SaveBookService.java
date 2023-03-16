package com.example.bookservice.service;

import com.example.bookservice.domain.Book;
import com.example.bookservice.domain.Member;
import com.example.bookservice.domain.SaveBook;
import com.example.bookservice.exception.CustomException;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.repository.MemberRepository;
import com.example.bookservice.repository.SaveBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static com.example.bookservice.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveBookService {

    private final SaveBookRepository saveBookRepository;

    private final MemberRepository memberRepository;

    private final BookRepository bookRepository;

    // 책 저장
    public Long save(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId).get();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CustomException(BOOK_NOT_FOUND));

        SaveBook saveBook = SaveBook.createSaveBook(book, member);
        saveBookRepository.save(saveBook);
        return saveBook.getId();
    }

    // 저장한 책 조회
    public SaveBook findById(Long saveBookId) {
        return saveBookRepository.findById(saveBookId)
                .orElseThrow(() -> new CustomException(BOOK_NOT_FOUND));
    }

    // 책 삭제
    public Long delete(Long saveBookId) {
        SaveBook saveBook = saveBookRepository.findById(saveBookId)
                .orElseThrow(() -> new CustomException(BOOK_NOT_FOUND));
        saveBookRepository.deleteById(saveBookId);
        log.info("[saveBooks] 삭제 완료");
        return saveBook.getId();
    }
}
