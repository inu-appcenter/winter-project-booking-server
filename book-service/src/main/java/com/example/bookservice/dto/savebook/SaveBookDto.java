package com.example.bookservice.dto.savebook;

import com.example.bookservice.domain.SaveBook;
import lombok.Data;

@Data
public class SaveBookDto {

    private Long id;

    private String title;

    private String author;

    public SaveBookDto(SaveBook saveBook) {
        this.id = saveBook.getId();
        this.title = saveBook.getTitle();;
        this.author = saveBook.getAuthor();
    }
}
