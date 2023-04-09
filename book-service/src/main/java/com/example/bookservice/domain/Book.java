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
    private String title;

    private String author;

    private String publisher;

    private int year;

    @Enumerated(EnumType.STRING)
    private BookType bookType;

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

    public void setBookType(BookType bookType) {this.bookType = bookType;}


    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getBooks().add(this);
    }

    public static Book createBook(Subject subject, String title, String author, String publisher, int year, BookType bookType) {
        Book book = new Book();
        book.setSubject(subject);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setBookType(bookType);
        return book;
    }
}
