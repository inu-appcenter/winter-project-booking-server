package com.example.bookservice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_book_id")
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private int year;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

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

    public static SaveBook createSaveBook(Book book, Member member) {
        SaveBook saveBook = new SaveBook();
        saveBook.title = book.getTitle();
        saveBook.author = book.getAuthor();
        saveBook.publisher = book.getPublisher();
        saveBook.year = book.getYear();
        saveBook.type = book.getType();
        saveBook.book = book;
        saveBook.member = member;
        return saveBook;
    }
}
