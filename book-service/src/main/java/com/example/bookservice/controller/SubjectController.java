package com.example.bookservice.controller;

import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectCondition;
import com.example.bookservice.dto.subject.SubjectDto;
import com.example.bookservice.dto.subject.SubjectResponse;
import com.example.bookservice.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "과목 조회")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@Slf4j
public class SubjectController {

    private final SubjectService subjectService;

    // 과목 검색
    @GetMapping
    @ApiOperation(value = "과목 전체 검색", notes = "과목 전체 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "과목 전체 검색 성공"),
            @ApiResponse(code = 404, message = "해당하는 과목이 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public List<SubjectDto> findSubjects(SubjectCondition subjectCondition) {
        log.info("[findSubjects] 과목명으로 검색을 수행합니다. subjectName : {}, professorName : {}, departmentName : {}", subjectCondition.getSubjectName(), subjectCondition.getProfessorName(), subjectCondition.getDepartmentName());
        List<Subject> subjects = subjectService.findSubjects(subjectCondition);
        return subjects.stream()
                .map(subject -> new SubjectDto(subject))
                .collect(Collectors.toList());
    }

    // 과목 조회
    @GetMapping("/{subjectId}")
    @ApiOperation(value = "과목 단건 조회", notes = "과목 ID에 해당하는 책 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "과목 단건 조회 성공"),
            @ApiResponse(code = 404, message = "해당하는 과목이 존재하지 않습니다"),
            @ApiResponse(code = 500, message = "서버에서 에러가 발생하였습니다")
    })
    public SubjectResponse findById(@PathVariable Long subjectId) {
        log.info("[findById] 과목 조회를 수행합니다. subjectId : {}", subjectId);
        Subject subject = subjectService.findById(subjectId);
        return new SubjectResponse(subject);
    }
}
