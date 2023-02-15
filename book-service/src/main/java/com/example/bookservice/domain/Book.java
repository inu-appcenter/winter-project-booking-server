package com.example.bookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String publisher;

    @Column
    private int year;

    @Column
    private String type;

    @Column
    private Boolean isSaved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChecked(Boolean checked) {
        isSaved = checked;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getBooks().add(this);
    }

    public static Book createBook(Subject subject, String title, String author, String publisher, int year, String type, Boolean isSaved) {
        Book book = new Book();
        book.setSubject(subject);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setType(type);
        book.setChecked(isSaved);
        return book;
    }
}
