package com.example.bookservice.dto.member;

import com.example.bookservice.domain.Member;
import com.example.bookservice.dto.savebook.SaveBookDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

    private Long id;

    private String name;

    private String college;

    private String department;

    private List<SaveBookDto> saveBookDtoList;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getCollege();
        this.college = member.getCollege();
        this.department = member.getDepartment();
        this.saveBookDtoList = member.getSaveBooks().stream()
                .map(saveBook -> new SaveBookDto(saveBook))
                .collect(Collectors.toList());
    }
}
