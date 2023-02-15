package com.example.bookservice.controller;


import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubjectDto {

    private Long id;

    private String name;

    private String professor;

    private String department;

    private SubjectType subjectType;

    public SubjectDto(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.professor = subject.getProfessor();
        this.department = subject.getDepartment();
        this.subjectType = subject.getSubjectType();
    }
}
