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
    @Column
    private String name;
    @Column
    private String professor;
    @Column
    private String department;
    @Column
    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @OneToMany(mappedBy = "subject")
    private List<Book> books = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

//    public void addBook(Book book) {
//        books.add(book);
//        book.setSubject(this);
//    }
//
//    public static Subject createSubject(Book... books) {
//        Subject subject = new Subject();
//        for (Book book : books) {
//            subject.addBook(book);
//        }
//        return subject;
//    }

    public Subject(String name, String professor, String department, SubjectType subjectType) {
        this.name = name;
        this.professor = professor;
        this.department = department;
        this.subjectType = subjectType;
    }
}
