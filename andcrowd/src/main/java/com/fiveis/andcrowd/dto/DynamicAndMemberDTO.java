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
    }

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class FindById {
        private int memberId;
        private int andId;
        private int userId;
        private boolean isDeleted;
    }
    
}
