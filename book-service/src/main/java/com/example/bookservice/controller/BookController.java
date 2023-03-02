package com.example.bookservice.controller;

import com.example.bookservice.domain.Book;
import com.example.bookservice.service.BookService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "책 조회")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping
    @ApiOperation(value = "책 전체 조회", notes = "책 전체에 대한 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "책 전체 조회 성공"),
            @ApiResponse(code = 404, message = "해당하는 책이 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public List<BookDto> findAll() {
        log.info("[findAll] 책 전체 조회를 합니다.");
        List<Book> books = bookService.findAll();
        return books.stream()
                .map(book -> new BookDto(book))
                .collect(Collectors.toList());
    }

    @GetMapping("/{bookId}")
    @ApiOperation(value = "책 단건 조회", notes = "책 ID에 해당하는 책 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "책 단건 조회 성공"),
            @ApiResponse(code = 404, message = "해당하는 책이 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public BookDto findById(@PathVariable Long bookId) {
        log.info("[findById] 책 조회를 합니다. id : {}", bookId);
        Book book = bookService.findById(bookId);
        return new BookDto(book);
    }
}
