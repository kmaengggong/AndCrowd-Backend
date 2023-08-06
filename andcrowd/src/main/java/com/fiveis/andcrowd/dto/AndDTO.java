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

        public DynamicAndBoardDTO(int andBoardId, int andId, int userId, Integer andBoardTag,
                                  String andBoardTitle, String andBoardContent, String andImg,
                                  LocalDateTime publishedAt, LocalDateTime updatedAt,
                                  Integer andBoardViewCount, boolean isDeleted) {
            this.andBoardId = andBoardId;
            this.andId = andId;
            this.userId = userId;
            this.andBoardTag = andBoardTag;
            this.andBoardTitle = andBoardTitle;
            this.andBoardContent = andBoardContent;
            this.andImg = andImg;
            this.publishedAt = publishedAt;
            this.updatedAt = updatedAt;
            this.andBoardViewCount = andBoardViewCount;
            this.isDeleted = isDeleted;
        }
    }

    //모임 분야
    public class AndCategoryDTO {
        private int andCategoryId;
        private String andCategoryName;
        private boolean isDeleted;

        public AndCategoryDTO(int andCategoryId, String andCategoryName, boolean isDeleted) {
            this.andCategoryId = andCategoryId;
            this.andCategoryName = andCategoryName;
            this.isDeleted = isDeleted;
        }
    }

    //모임 참여자
    public class DynamicAndMemberDTO {
        private int memberId;
        private int andId;
        private int userId;
        private boolean isDeleted;

        public DynamicAndMemberDTO(int memberId, int andId, int userId, boolean isDeleted) {
            this.memberId = memberId;
            this.andId = andId;
            this.userId = userId;
            this.isDeleted = isDeleted;
        }
    }

}
