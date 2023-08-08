package com.fiveis.andcrowd.dto;

import lombok.*;

import java.time.LocalDateTime;

public class ProjectDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Find{
        private int projectId;
        private int projectType;
        private int projectHeaderImg;
        private int projectTitle;
    }
}
