package com.example.bookservice.controller;


import com.example.bookservice.domain.Member;
import com.example.bookservice.domain.SaveBook;
import com.example.bookservice.dto.member.DefaultResponseDto;
import com.example.bookservice.dto.savebook.SaveBookResponseDto;
import com.example.bookservice.service.SaveBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Api(tags = "책 저장 관리")
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@Slf4j
public class SaveBookController {

    private final SaveBookService saveBookService;

    // 책 저장
    @PostMapping("/{bookId}/save-books")
    @ApiOperation(value = "책 저장", notes = "책 저장을 진행합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "책 저장 완료"),
            @ApiResponse(code = 404, message = "책 정보가 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public Long save(@PathVariable Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        log.info("memberEmail : {}, memberName : {}, memberPassword : ****", member.getEmail(), member.getName());
        Long saveBook = saveBookService.save(member.getId(), bookId);

        return saveBook;
    }

    // 저장한 책 조회
    @GetMapping("/save-books/{saveBookId}")
    @ApiOperation(value = "저장한 책 조회", notes = "저장한 책의 정보를 조회합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "저장한 책 조회 성공"),
            @ApiResponse(code = 404, message = "저장한 책 정보가 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public SaveBookResponseDto findById(@PathVariable Long saveBookId) {
        SaveBook saveBook = saveBookService.findById(saveBookId);
        return new SaveBookResponseDto(saveBook);
    }

    // 책 삭제
    @DeleteMapping("/save-books/{saveBookId}")
    @ApiOperation(value = "저장한 책 삭제", notes = "저장한 책의 정보를 삭제합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "저장한 책 삭제 성공"),
            @ApiResponse(code = 404, message = "저장한 책 정보가 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public DefaultResponseDto delete(@PathVariable Long saveBookId) {
        log.info("[saveBook 삭제 시작]");
        saveBookService.delete(saveBookId);
        return DefaultResponseDto.builder()
                .success(true)
                .msg("책 삭제 완료")
                .build();
    }
}
