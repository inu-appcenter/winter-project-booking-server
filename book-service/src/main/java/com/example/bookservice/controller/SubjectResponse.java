package com.example.bookservice.controller;

import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubjectResponse {

    private Long id;

    private String name;

    private String professor;

    private String department;

    private SubjectType subjectType;

    private List<BookDto> books;

    public SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.professor = subject.getProfessor();
        this.department = subject.getDepartment();
        this.subjectType = subject.getSubjectType();
        this.books = subject.getBooks().stream()
                .map(book -> new BookDto(book))
                .collect(Collectors.toList());
    }
}
