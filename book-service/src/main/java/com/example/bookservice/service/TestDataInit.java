package com.example.bookservice.service;

import com.example.bookservice.domain.Book;
import com.example.bookservice.domain.BookType;
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
        Subject subject10 = new Subject("디지털공학", "김우일", "컴퓨터공학부", SubjectType.전공심화);
        Subject subject11 = new Subject("기계학습", "김우일", "컴퓨터공학부", SubjectType.전공심화);

        Book book1 = Book.createBook(subject1, "핵심 미분적분학(9판)", null, "경문사", 2021, BookType.주교재);
        Book book2 = Book.createBook(subject2, "4차 산업혁명 시대의 이산수학 (개정판)", null, "생능출판사", 2022, BookType.주교재);
        Book book3 = Book.createBook(subject3, "소프트웨어 중심사회의 컴퓨터 개론", null, "인피니티", 2019, BookType.주교재);
        Book book4 = Book.createBook(subject4, "미적분학", null, "한빛미디어", 2015, BookType.주교재);
        Book book5 = Book.createBook(subject5, "STEM CookBook, 컴퓨팅 사고력을 키우는 이산수학 (3판)", null, "한빛아카데미", 2021, BookType.주교재);
        Book book6 = Book.createBook(subject6, "Advanced Calculus for Applications", null, "Prentice-Hall", 1976, BookType.주교재);
        Book book7 = Book.createBook(subject7, "공업수학 Express", null, "생능출판사", 2020, BookType.주교재);
        Book book8 = Book.createBook(subject8, "쉽게 풀어쓴 C언어 익스프레스", null, "생능출판사", 2021, BookType.주교재);
        Book book9 = Book.createBook(subject9, "Mathematics for Electrical Engineering and Computing", null, "Newnes", 2004, BookType.주교재);
        Book book10 = Book.createBook(subject10, "Digital Design, 5th Ed.", "M. Morris Mano and Michael D. Ciletti", "Pearson", 20131201, BookType.주교재);
        Book book11 = Book.createBook(subject10, "Digital Design with RTL Design, Verilog & VHDL, 2nd Ed.","Frank Vahid", "John Wiley & Sons", 0, BookType.참고서적);
        Book book12 = Book.createBook(subject10, "Fundamentals of Digital Logic with VHDL Design, 3rd Ed.","Stephen Brown & Zvon", "McGraw-Hill", 0, BookType.참고서적);
        Book book13 = Book.createBook(subject11, "기계학습", "오일석", "한빛아카데미", 2017, BookType.주교재);
        Book book14 = Book.createBook(subject11, "패턴인식개론", "한학용", "한빛아카데미", 2014, BookType.주교재);
        Book book15 = Book.createBook(subject11, "Pattern Recognition and Machine Learning", "Christopher M. Bishop", "Springer", 2011, BookType.참고서적);

        for (Subject subject : Arrays.asList(subject1, subject2, subject3, subject4, subject5, subject6, subject7, subject8, subject9, subject10, subject11)) {
            subjectRepository.save(subject);
        }

        for (Book book : Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, book11, book12, book13, book14, book15)) {
            bookRepository.save(book);
        }
    }
}
