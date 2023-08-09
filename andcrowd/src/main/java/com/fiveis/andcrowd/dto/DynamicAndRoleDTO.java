package com.fiveis.andcrowd.dto;

import lombok.*;

import java.time.LocalDateTime;

public class DynamicAndRoleDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindById {
        private int andRoleId;
        private int andId;
        private String andRole;
        private int andRoleLimit;
        private boolean isDeleted;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private int andRoleId;
        private int andId;
        private String andRole;
        private int andRoleLimit;
        private boolean isDeleted;
    }
}
