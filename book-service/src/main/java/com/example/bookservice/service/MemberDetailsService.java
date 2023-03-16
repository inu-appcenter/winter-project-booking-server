package com.example.bookservice.service;

import com.example.bookservice.exception.CustomException;
import com.example.bookservice.exception.ErrorCode;
import com.example.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.bookservice.exception.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[loadUserByUsername] loadUserByUsername 수행. username : {}", username);
        return memberRepository.getByEmail(username)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }
}
