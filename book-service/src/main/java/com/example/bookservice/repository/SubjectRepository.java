package com.example.bookservice.repository;

import com.example.bookservice.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findBySubjectNameContaining(String subjectName);

    List<Subject> findByProfessorNameContaining(String professorName);

    List<Subject> findByDepartmentNameContaining(String departmentName);
}
