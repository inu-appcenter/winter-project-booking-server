package com.example.bookservice.dto.subject;


import com.example.bookservice.domain.Subject;
import com.example.bookservice.domain.SubjectType;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

@Getter
public class SubjectDto {

    @ApiModelProperty(example = "1")
    @ApiParam(value = "과목 ID")
    private Long id;

    @ApiModelProperty(example = "융합디자인론")
    @ApiParam(value = "과목명")
    private String subjectName;

    @ApiModelProperty(example = "한혜진")
    @ApiParam(value = "교수명")
    private String professorName;

    @ApiModelProperty(example = "디자인학부")
    @ApiParam(value = "전공 (교양수업인 경우 교양)")
    private String departmentName;

    @ApiModelProperty(example = "전공심화")
    @ApiParam(value = "과목분류 (전공핵심, 교양심화 등)")
    private SubjectType subjectType;

    public SubjectDto(Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.professorName = subject.getProfessorName();
        this.departmentName = subject.getDepartmentName();
        this.subjectType = subject.getSubjectType();
    }
}
