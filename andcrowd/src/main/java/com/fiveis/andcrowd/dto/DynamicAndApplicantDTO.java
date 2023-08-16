package com.fiveis.andcrowd.dto;

import lombok.*;

public class DynamicAndApplicantDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindById {
        private int andApplyId;
        private int andId;
        private int userId;
        private int andRoleId;
        private String andApplyContent;
        private int andApplyStatus;
        private boolean isDeleted;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private int andApplyId;
        private int andId;
        private int userId;
        private int andRoleId;
        private String andApplyContent;
        private int andApplyStatus; // 1: 합격, 2: 보류, 3: 탈락
        private boolean isDeleted;
    }
}
