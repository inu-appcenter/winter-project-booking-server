package com.example.bookservice.service;

import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectCondition;
import com.example.bookservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {

    private final SubjectRepository subjectRepository;

    // 과목 검색
    public List<Subject> findSubjects(SubjectCondition subjectCondition) {
        String name = subjectCondition.getName();
        String professor = subjectCondition.getProfessor();
        String department = subjectCondition.getDepartment();
        if (StringUtils.hasText(name)) {
            return subjectRepository.findByNameContaining(name);
        } else if (StringUtils.hasText(professor)) {
            return subjectRepository.findByProfessorContaining(professor);
        } else if (StringUtils.hasText(department)) {
            return subjectRepository.findByDepartmentContaining(department);
        }
        return subjectRepository.findAll();
    }

    // 과목 조회
    public Subject findById(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException());
    }
}
