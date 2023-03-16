package com.example.bookservice.repository;

import com.example.bookservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> getByEmail(String email);
}
