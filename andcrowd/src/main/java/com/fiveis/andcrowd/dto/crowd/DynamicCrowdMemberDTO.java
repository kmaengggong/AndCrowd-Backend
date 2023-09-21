package com.fiveis.andcrowd.dto.crowd;

import lombok.*;

public class DynamicCrowdMemberDTO {

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private int memberId;
        private int crowdId;
        private int userId;
        private boolean isDeleted;
    }

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindByCrowdMemberId {
        private int memberId;
        private int crowdId;
        private int userId;
        private boolean isDeleted;
    }

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberList {
        private int memberId;
        private int crowdId;
        private int userId;
        private boolean isDeleted;
        private String nickName;
    }
}
