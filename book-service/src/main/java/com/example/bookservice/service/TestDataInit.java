package com.example.bookservice.service;

import com.example.bookservice.domain.Book;
import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectType;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final SubjectRepository subjectRepository;
    private final BookRepository bookRepository;

    /**
     * 확인용 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("[initData] 테스트 데이터 추가");

        Subject subject1 = new Subject("대학수학(1)", "박성화", "컴퓨터공학부", SubjectType.기초교양);
        Subject subject2 = new Subject("이산수학", "이승수", "컴퓨터공학부", SubjectType.전공기초);
        Subject subject3 = new Subject("컴퓨터공학개론", "전혜경", "컴퓨터공학부", SubjectType.전공기초);
        Subject subject4 = new Subject("대학수학(1)", "오승호", "정보통신공학과", SubjectType.기초교양);
        Subject subject5 = new Subject("이산수학론", "우요섭", "정보통신공학과", SubjectType.전공심화);
        Subject subject6 = new Subject("공업수학", "이종길", "정보통신공학과", SubjectType.전공기초);
        Subject subject7 = new Subject("대학수학(1)", "강우철", "임베디드시스템공학과", SubjectType.기초교양);
        Subject subject8 = new Subject("C언어프로그래밍(1)", "전경구", "임베디드시스템공학과", SubjectType.전공기초);
        Subject subject9 = new Subject("이산수학", "황광일", "임베디드시스템공학과", SubjectType.전공기초);

        Book book1 = Book.createBook(subject1, "핵심 미분적분학(9판)", null, "경문사", 2021, null);
        Book book2 = Book.createBook(subject2, "4차 산업혁명 시대의 이산수학 (개정판)", null, "생능출판사", 2022, null);
        Book book3 = Book.createBook(subject3, "소프트웨어 중심사회의 컴퓨터 개론", null, "인피니티", 2019, null);
        Book book4 = Book.createBook(subject4, "미적분학", null, "한빛미디어", 2015, null);
        Book book5 = Book.createBook(subject5, "STEM CookBook, 컴퓨팅 사고력을 키우는 이산수학 (3판)", null, "한빛아카데미", 2021, null);
        Book book6 = Book.createBook(subject6, "Advanced Calculus for Applications", null, "Prentice-Hall", 1976, null);
        Book book7 = Book.createBook(subject7, "공업수학 Express", null, "생능출판사", 2020, null);
        Book book8 = Book.createBook(subject8, "쉽게 풀어쓴 C언어 익스프레스", null, "생능출판사", 2021, null);
        Book book9 = Book.createBook(subject9, "Mathematics for Electrical Engineering and Computing", null, "Newnes", 2004, null);

        for (Subject subject : Arrays.asList(subject1, subject2, subject3, subject4, subject5, subject6, subject7, subject8, subject9)) {
            subjectRepository.save(subject);
        }

        for (Book book : Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9)) {
            bookRepository.save(book);
        }
    }
}
