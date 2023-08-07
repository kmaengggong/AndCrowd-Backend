package com.fiveis.andcrowd.dto;

import lombok.*;

import java.time.LocalDateTime;

public class CrowdDTO {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class FindById {
        // findById시 필요한 멤버변수
        private int crowdId;
        private int adNum;
        private int andId;
        private int crowdCategoryId;
        private String crowdContent;
        private LocalDateTime crowdEndDate;
        private int crowdGoal;
        private String crowdImg1;
        private String crowdImg2;
        private String crowdImg3;
        private String crowdImg4;
        private String crowdImg5;
        private int crowdStatus; // 1:심사, 2:반려, 3:종료, 0:펀딩중
        private String crowdTitle;
        private String headerImg;
        private boolean isDeleted;
        private int likeSum;
        private LocalDateTime publishedAt;
        private LocalDateTime updatedAt;
        private int userId;
        private int viewCount;
    }

    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        // UpdateRequest시 필요한 멤버변수 : 크라우드Id, 본문, 마감일, 사진 1-5, 제목, 대표사진
        private int crowdId;
        private String crowdContent;
        private LocalDateTime crowdEndDate;
        private String crowdImg1;
        private String crowdImg2;
        private String crowdImg3;
        private String crowdImg4;
        private String crowdImg5;
        private String crowdTitle;
        private String headerImg;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class FindAllByUserId {
        //response(findAll)시 필요한 멤버변수 : 크라우드Id, 크라우드분야Id, 본문, 마감일, 목표금액, 상태, 제목, 대표사진, 업로드시간, 수정시간, 유저Id, 삭제여부
        private int crowdId;
        private int crowdCategoryId;
        private String crowdContent;
        private LocalDateTime crowdEndDate;
        private int crowdGoal;
        private int crowdStatus; // 1:심사, 2:반려, 3:종료, 0:펀딩중
        private String crowdTitle;
        private String headerImg;
        private LocalDateTime publishedAt;
        private LocalDateTime updatedAt;
        private int userId;
        private boolean isDeleted;
    }

    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Insert {
        // Insert(CreateRequest)시 필요한 멤버변수 : 크라우드Id, 광고멤버십Id, 크라우드분야Id, 본문, 마감일, 목표금액, 사진 1-5, 상태코드, 제목, 대표사진, 회원Id
        private int crowdId;
        private int adNum;
        private int crowdCategoryId;
        private String crowdContent;
        private LocalDateTime crowdEndDate;
        private int crowdGoal;
        private String crowdImg1;
        private String crowdImg2;
        private String crowdImg3;
        private String crowdImg4;
        private String crowdImg5;
        private int crowdStatus; // 1:심사, 2:반려, 3:종료, 0:펀딩중
        private String crowdTitle;
        private String headerImg;
        private int userId;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class DeleteById {
        // DeleteRequest시 필요한 멤버변수 : 크라우드Id
        private int crowdId;

        public DeleteById(int crowdId) {
            this.crowdId = crowdId;
        }
    }

    @Getter @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicCrowdBoard{ // 크라우드 게시판
        private int crowdBoardId;
        private int crowdId;
        private int crowdBoardTag;
        private String crowdBoardTitle;
        private String crowdBoardContent;
        private String crowdImg;
        private LocalDateTime publishedAt;
        private LocalDateTime updatedAt;
        private int viewCount;
        private boolean isDeleted;
    }

    @Getter @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CrowdCategory{ // 크라우드 분야
        private int crowdCategoryId;
        private String crowdCategoryName;
        private boolean isDeleted;
    }

    @Getter @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicSponsor{ // 크라우드 후원자
        private int sponsorId;
        private int crowdId;
        private int purchaseId;
    }

}
