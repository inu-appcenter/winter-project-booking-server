package com.example.bookservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private Boolean isCheckOuted;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCheckOuted(Boolean checkOuted) {
        isCheckOuted = checkOuted;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
