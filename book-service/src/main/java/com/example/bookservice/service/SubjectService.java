package com.example.bookservice.service;

import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectCondition;
import com.example.bookservice.exception.CustomException;
import com.example.bookservice.exception.ErrorCode;
import com.example.bookservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.bookservice.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {

    private final SubjectRepository subjectRepository;

    // 과목 검색
    public List<Subject> findSubjects(SubjectCondition subjectCondition) {
        List<Subject> foundSubjects = null;

        String name = subjectCondition.getName();
        String professor = subjectCondition.getProfessor();
        String department = subjectCondition.getDepartment();

        try
        {
            if (StringUtils.hasText(name)) {
                return subjectRepository.findByNameContaining(name);
            } else if (StringUtils.hasText(professor)) {
                return subjectRepository.findByProfessorContaining(professor);
            } else if (StringUtils.hasText(department)) {
                return subjectRepository.findByDepartmentContaining(department);
            }
            foundSubjects = subjectRepository.findAll();
        }
        catch (RuntimeException e)
        {
            throw new CustomException(SERVER_ERROR);
        }

        if (foundSubjects.isEmpty())
        {
            throw new CustomException(SUBJECT_NOT_FOUND);
        }

        return foundSubjects;
    }

    // 과목 조회
    public Subject findById(Long subjectId) {
        Subject foundSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new CustomException(ErrorCode.SUBJECT_NOT_FOUND));

        return foundSubject;
    }
}
