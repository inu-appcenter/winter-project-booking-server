package com.example.bookservice.domain;

import lombok.AllArgsConstructor;
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
    private Long id;
    @Column
    private String name;
    @Column
    private String professor;
    @Column
    private String department;
    @Column
    private String category;
    @OneToMany(mappedBy = "subject")
    private List<Book> bookList = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Subject(String name, String professor, String department, String category, List<Book> bookList) {
        this.name = name;
        this.professor = professor;
        this.department = department;
        this.category = category;
        this.bookList = bookList;
    }
}
