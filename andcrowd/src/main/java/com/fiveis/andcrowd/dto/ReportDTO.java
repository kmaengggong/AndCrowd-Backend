package com.fiveis.andcrowd.dto;

import lombok.*;

import java.time.LocalDateTime;

public class ReportDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Find{
        int reportId;
        int userId;
        int projectId;
        int projectType;
        String reportContent;
        LocalDateTime reportDate;
        int reportStatus;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        int reportId;
        int reportStatus;
    }
}
