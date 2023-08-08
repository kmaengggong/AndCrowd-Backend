package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.Crowd;
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

        public FindById(Crowd crowd){
            this.crowdId = crowd.getCrowdId();
            this.adNum = crowd.getAdNum();
            this.andId = crowd.getAndId();
            this.crowdCategoryId = crowd.getCrowdCategoryId();
            this.crowdContent = crowd.getCrowdContent();
            this.crowdEndDate = crowd.getCrowdEndDate();
            this.crowdGoal = crowd.getCrowdGoal();
            this.crowdImg2 = crowd.getCrowdImg2();
            this.crowdImg3 = crowd.getCrowdImg3();
            this.crowdImg4 = crowd.getCrowdImg4();
            this.crowdImg5 = crowd.getCrowdImg5();
            this.crowdStatus = crowd.getCrowdStatus();
            this.crowdTitle = crowd.getCrowdTitle();
            this.headerImg = crowd.getHeaderImg();
            this.isDeleted = crowd.isDeleted();
            this.likeSum = crowd.getLikeSum();
            this.publishedAt = crowd.getPublishedAt();
            this.updatedAt = crowd.getUpdatedAt();
            this.userId = crowd.getUserId();
            this.viewCount = crowd.getViewCount();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public class Update {
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

        public Update(Crowd crowd) { // UpdateRequest 생성자
            this.crowdId = crowd.getCrowdId();
            this.crowdCategoryId = crowd.getCrowdCategoryId();
            this.crowdContent = crowd.getCrowdContent();
            this.crowdEndDate = crowd.getCrowdEndDate();
            this.crowdImg1 = crowd.getCrowdImg1();
            this.crowdImg2 = crowd.getCrowdImg2();
            this.crowdImg3 = crowd.getCrowdImg3();
            this.crowdImg4 = crowd.getCrowdImg4();
            this.crowdImg5 = crowd.getCrowdImg5();
            this.crowdTitle = crowd.getCrowdTitle();
            this.headerImg = crowd.getHeaderImg();
        }
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

        public FindAllByUserId(Crowd crowd){
            this.crowdId = crowd.getCrowdId();
            this.crowdCategoryId = crowd.getCrowdCategoryId();
            this.crowdContent = crowd.getCrowdContent();
            this.crowdEndDate = crowd.getCrowdEndDate();
            this.crowdGoal = crowd.getCrowdGoal();
            this.crowdStatus = crowd.getCrowdStatus();
            this.crowdTitle = crowd.getCrowdTitle();
            this.headerImg = crowd.getHeaderImg();
            this.publishedAt = crowd.getPublishedAt();
            this.updatedAt = crowd.getUpdatedAt();
            this.userId = crowd.getUserId();
            this.isDeleted = crowd.isDeleted();
        }
    }

    @Getter
    @Setter
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

        public Insert(Crowd crowd){
            this.crowdId = crowd.getCrowdId();
            this.adNum = crowd.getAdNum();
            this.crowdCategoryId = crowd.getCrowdCategoryId();
            this.crowdContent = crowd.getCrowdContent();
            this.crowdEndDate = crowd.getCrowdEndDate();
            this.crowdGoal = crowd.getCrowdGoal();
            this.crowdImg1 = crowd.getCrowdImg1();
            this.crowdImg2 = crowd.getCrowdImg2();
            this.crowdImg3 = crowd.getCrowdImg3();
            this.crowdImg4 = crowd.getCrowdImg4();
            this.crowdImg5 = crowd.getCrowdImg5();
            this.crowdStatus = crowd.getCrowdStatus();
            this.crowdTitle = crowd.getCrowdTitle();
            this.headerImg = crowd.getHeaderImg();
            this.userId = crowd.getUserId();
        }
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

}
