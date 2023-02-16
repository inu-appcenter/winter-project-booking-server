package com.example.bookservice.service;

import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectCondition;
import com.example.bookservice.domain.SubjectType;
import com.example.bookservice.repository.SubjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubjectServiceTest {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectService subjectService;

    @Test
    void search() {
        //given
        Subject subject1 = new Subject("c++", "이선정", "컴퓨터공학부", SubjectType.전공심화);
        Subject subject2 = new Subject("컴퓨터구조", "허혜선", "컴퓨터공학부", SubjectType.전공핵심);

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        //when
        List<Subject> subjects1 = subjectService.findSubjects(new SubjectCondition("c", null, null));
        List<Subject> subjects2 = subjectService.findSubjects(new SubjectCondition(null, "이선", null));
        List<Subject> subjects3 = subjectService.findSubjects(new SubjectCondition(null, null, "컴퓨터"));
        //then
        Assertions.assertThat(subjects1.size()).isEqualTo(1);
        Assertions.assertThat(subjects2.size()).isEqualTo(1);
        Assertions.assertThat(subjects3.size()).isEqualTo(2);
    }

}