package com.fiveis.andcrowd.dto;

import lombok.*;

import java.time.LocalDateTime;

public class AndDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindById {

        private int andId;
        private int userId;
        private int andCategoryId;
        private int fundingId;
        private String andTitle;
        private String andHeaderImg;
        private String andContent;
        private LocalDateTime andEndDate;
        private int needNumMem;
        private String andImg1;
        private String andImg2;
        private String andImg3;
        private String andImg4;
        private String andImg5;
        private LocalDateTime publishedAt;
        private LocalDateTime updatedAt;
        private int andLikeCount;
        private int andViewCount;
        private int andStatus;
        private int adMembershipNum;
        private boolean isDeleted;

    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private int andId;
        private int userId;
        private int andCategoryId;
        private int fundingId;
        private String andTitle;
        private String andHeaderImg;
        private String andContent;
        private LocalDateTime andEndDate;
        private int needNumMem;
        private String andImg1;
        private String andImg2;
        private String andImg3;
        private String andImg4;
        private String andImg5;
        private LocalDateTime publishedAt;
        private LocalDateTime updatedAt;
        private int adMembershipNum;
        private boolean isDeleted;

    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindAllByUserId {
        private int andId;
        private int userId;
        private String andTitle;
        private String andHeaderImg;
        private LocalDateTime publishedAt;
        private LocalDateTime updatedAt;
        private int andStatus;
        private int adMembershipNum;
        private boolean isDeleted;

    }
    
    //모임 게시판
    @Getter @Setter @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class DynamicAndBoardDTO {
        private int andBoardId;
        private int andId;
        private int userId;
        private Integer andBoardTag;
        private String andBoardTitle;
        private String andBoardContent;
        private String andImg;
        private LocalDateTime publishedAt;
        private LocalDateTime updatedAt;
        private Integer andBoardViewCount;
        private boolean isDeleted;

    }

    //모임 분야
    @Getter @Setter @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public class AndCategoryDTO {
        private int andCategoryId;
        private String andCategoryName;
        private boolean isDeleted;
    }

    //모임 참여자
    @Getter @Setter @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public class DynamicAndMemberDTO {
        private int memberId;
        private int andId;
        private int userId;
        private boolean isDeleted;
    }

}
