package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.DynamicAndMember;
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
        public Update (DynamicAndMember dynamicAndMember){
            this.memberId = dynamicAndMember.getMemberId();
            this.andId = dynamicAndMember.getAndId();
            this.userId = dynamicAndMember.getUserId();
            this.isDeleted = dynamicAndMember.isDeleted();
        }
    }

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class findById {
        private int memberId;
        private int andId;
        private int userId;
        private boolean isDeleted;

        public findById(DynamicAndMember dynamicAndMember){
            this.memberId = dynamicAndMember.getMemberId();
            this.andId = dynamicAndMember.getAndId();
            this.userId = dynamicAndMember.getUserId();
            this.isDeleted = dynamicAndMember.isDeleted();
        }
    }
}
