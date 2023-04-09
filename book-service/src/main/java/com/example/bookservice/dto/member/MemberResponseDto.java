package com.example.bookservice.dto.member;

import com.example.bookservice.domain.Member;
import com.example.bookservice.dto.savebook.SaveBookDto;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberResponseDto {

    @ApiModelProperty(example = "1")
    @ApiParam(value = "회원 ID")
    private Long id;

    @ApiModelProperty(example = "이주원")
    @ApiParam(value = "회원 이름")
    private String name;

    @ApiModelProperty(example = "정보기술대학")
    @ApiParam(value = "단과대")
    private String college;

    @ApiModelProperty(example = "학부")
    @ApiParam(value = "컴퓨터공학부")
    private String department;

    @ApiModelProperty(example = "저장한 책 목록")
    @ApiParam(value = "저장한 책 목록")
    private List<SaveBookDto> saveBookList;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.college = member.getCollege();
        this.department = member.getDepartment();
        this.saveBookList = member.getSaveBooks().stream()
                .map(saveBook -> new SaveBookDto(saveBook))
                .collect(Collectors.toList());
    }
}
