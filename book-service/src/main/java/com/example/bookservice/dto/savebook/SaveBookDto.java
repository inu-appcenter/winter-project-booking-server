package com.example.bookservice.dto.savebook;

import com.example.bookservice.domain.SaveBook;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

@Getter
public class SaveBookDto {

    @ApiModelProperty(example = "1")
    @ApiParam(value = "저장된 리스트의 ID")
    private Long id;

    @ApiModelProperty(example = "1")
    @ApiParam(value = "저장된 책의 원래 ID")
    private Long bookId;

    @ApiModelProperty(example = "가깝고 먼 이야기, 색: 다채롭고 신비한 예술")
    @ApiParam(value = "책 제목")
    private String title;

    @ApiModelProperty(example = "한혜진")
    @ApiParam(value = "저자")
    private String author;

    public SaveBookDto(SaveBook saveBook) {
        this.id = saveBook.getId();
        this.bookId = saveBook.getBook().getId();
        this.title = saveBook.getTitle();;
        this.author = saveBook.getAuthor();
    }
}
