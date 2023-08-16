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
        private int adId;
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
        private int crowdStatus; // 1:펀딩중, 2:반려, 3:종료, 0:심사중
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
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        // UpdateRequest시 필요한 멤버변수 : 크라우드Id, 카테고리Id, 본문, 마감일, 사진 1-5, 제목, 대표사진
        private int crowdId;
        private int crowdCategoryId;
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
        private int crowdStatus; // 1:펀딩중, 2:반려, 3:종료, 0:심사중
        private String crowdTitle;
        private String headerImg;
        private LocalDateTime publishedAt;
        private LocalDateTime updatedAt;
        private int userId;
        private boolean isDeleted;

    }

}
