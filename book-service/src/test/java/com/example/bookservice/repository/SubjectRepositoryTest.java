package com.example.bookservice.repository;

import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectCondition;
import com.example.bookservice.domain.SubjectType;
import com.example.bookservice.service.SubjectService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubjectRepositoryTest {

    @Autowired
    SubjectRepository subjectRepository;

    @Test
    void search() {
        //given
        Subject subject1 = new Subject("c++", "이선정", "컴퓨터공학부", SubjectType.전공심화);
        Subject subject2 = new Subject("컴퓨터구조", "허혜선", "컴퓨터공학부", SubjectType.전공핵심);

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        //when
        List<Subject> subjects1 = subjectRepository.findAll();
        List<Subject> subjects2 = subjectRepository.findByNameContaining("c");
        List<Subject> subjects3 = subjectRepository.findByProfessorContaining("이선");
        List<Subject> subjects4 = subjectRepository.findByDepartmentContaining("컴퓨터");

        //then
        Assertions.assertThat(subjects1.size()).isEqualTo(2);
        Assertions.assertThat(subjects2.size()).isEqualTo(1);
        Assertions.assertThat(subjects3.size()).isEqualTo(1);
        Assertions.assertThat(subjects4.size()).isEqualTo(2);
    }
}