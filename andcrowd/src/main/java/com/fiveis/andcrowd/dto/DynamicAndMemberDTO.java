package com.fiveis.andcrowd.dto;

import lombok.*;

public class DynamicAndMemberDTO {
    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class Update {
        private int memberId;
        private int andId;
        private int userId;
        private boolean isDeleted;
    }

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class FindByMemberId {
        private int memberId;
        private int andId;
        private int userId;
        private boolean isDeleted;
    }
    
}
