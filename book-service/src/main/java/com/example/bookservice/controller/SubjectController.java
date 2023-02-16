package com.example.bookservice.controller;

import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectCondition;
import com.example.bookservice.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@Slf4j
public class SubjectController {

    private final SubjectService subjectService;

    // 과목 검색
    @GetMapping
    public List<SubjectDto> findSubjects(SubjectCondition subjectCondition) {
        log.info("[findSubjects] 과목명으로 검색을 수행합니다. name : {}, professor : {}, department : {}", subjectCondition.getName(), subjectCondition.getProfessor(), subjectCondition.getDepartment());
        List<Subject> subjects = subjectService.findSubjects(subjectCondition);
        return subjects.stream()
                .map(subject -> new SubjectDto(subject))
                .collect(Collectors.toList());
    }

    // 과목 조회
    @GetMapping("/{subjectId}")
    public SubjectResponse findById(@PathVariable Long subjectId) {
        log.info("[findById] 과목 조회를 수행합니다. subjectId : {}", subjectId);
        Subject subject = subjectService.findById(subjectId);
        return new SubjectResponse(subject);
    }
}
