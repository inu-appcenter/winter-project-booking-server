package com.example.bookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    CULTURE_ESSENTIAL("교양핵심"),
    CULTURE_INTENSIFICATION("교양심화"),
    MAJOR_BASE("전공기초"),
    MAJOR_ESSENTIAL("전공핵심"),
    MAJOR_INTENSIFICATION("전공심화");

    private final String name;
}
