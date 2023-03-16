package com.example.bookservice.repository;

import com.example.bookservice.domain.SaveBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveBookRepository extends JpaRepository<SaveBook, Long> {

}
