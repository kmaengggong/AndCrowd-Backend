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
        private int roleId;
        private String and_apply_content;
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
        private int roleId;
        private String and_apply_content;
        private boolean isDeleted;
    }
}
