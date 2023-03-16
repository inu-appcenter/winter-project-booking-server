package com.example.bookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;
    private String subjectName;
    private String professorName;
    private String departmentName;
    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @OneToMany(mappedBy = "subject")
    private List<Book> books = new ArrayList<>();

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }


    public Subject(String subjectName, String professorName, String departmentName, SubjectType subjectType) {
        this.subjectName = subjectName;
        this.professorName = professorName;
        this.departmentName = departmentName;
        this.subjectType = subjectType;
    }
}
