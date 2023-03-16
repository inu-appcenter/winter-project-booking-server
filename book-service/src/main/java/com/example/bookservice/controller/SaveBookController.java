package com.example.bookservice.controller;


import com.example.bookservice.domain.Member;
import com.example.bookservice.domain.SaveBook;
import com.example.bookservice.dto.savebook.SaveBookResponse;
import com.example.bookservice.service.SaveBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
public class SaveBookController {

    private final SaveBookService saveBookService;

    // 책 저장
    @PostMapping("/{bookId}/save-books")
    public Long save(@PathVariable Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)authentication.getPrincipal();
        log.info("memberEmail : {}, memberName : {}, memberPassword : ****", member.getEmail(), member.getName());
        Long saveBook = saveBookService.save(member.getId(), bookId);

        return saveBook;
    }

    // 저장한 책 조회
    @GetMapping("/save-books/{saveBookId}")
    public SaveBookResponse findById (@PathVariable Long saveBookId) {
        SaveBook saveBook = saveBookService.findById(saveBookId);
        return new SaveBookResponse(saveBook);
    }

    // 책 삭제
    @DeleteMapping("/save-books/{saveBookId}")
    public ResponseResult delete(@PathVariable Long saveBookId) {
        log.info("[saveBook 삭제 시작]");
        saveBookService.delete(saveBookId);
        return new ResponseResult(true, "책 삭제 완료");
    }
}
