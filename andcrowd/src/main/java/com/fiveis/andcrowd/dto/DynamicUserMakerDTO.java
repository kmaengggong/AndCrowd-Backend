package com.fiveis.andcrowd.dto;

import lombok.*;

public class DynamicUserMakerDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Find{
        private int uMakerId;
        private int projectId;
        private int projectType;
    }
}
