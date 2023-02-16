package com.example.bookservice.repository;

import com.example.bookservice.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByNameContaining(String name);

    List<Subject> findByProfessorContaining(String professor);

    List<Subject> findByDepartmentContaining(String department);
}
