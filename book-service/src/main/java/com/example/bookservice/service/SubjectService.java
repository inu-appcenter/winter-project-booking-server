package com.example.bookservice.service;

import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectCondition;
import com.example.bookservice.exception.CustomException;
import com.example.bookservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.bookservice.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SubjectService {

    private final SubjectRepository subjectRepository;

    // 과목 검색
    @Transactional
    public List<Subject> findSubjects(SubjectCondition subjectCondition) {
        List<Subject> foundSubjects = null;

        String subjectName = subjectCondition.getSubjectName();
        String professorName = subjectCondition.getProfessorName();
        String departmentName = subjectCondition.getDepartmentName();

        try
        {
            if (StringUtils.hasText(subjectName)) {
                return subjectRepository.findBySubjectNameContaining(subjectName);
            } else if (StringUtils.hasText(professorName)) {
                return subjectRepository.findByProfessorNameContaining(professorName);
            } else if (StringUtils.hasText(departmentName)) {
                return subjectRepository.findByDepartmentNameContaining(departmentName);
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
    @Transactional
    public Subject findById(Long subjectId) {
        Subject foundSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new CustomException(SUBJECT_NOT_FOUND));

        return foundSubject;
    }
}
