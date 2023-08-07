package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.DynamicAndBoard;
import lombok.*;

import java.time.LocalDateTime;

public class DynamicAndBoardDTO {

    //모임 게시판 업데이트
    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class Update {
        private int andBoardId;
        private int userId;
        private Integer andBoardTag;
        private String andBoardTitle;
        private String andBoardContent;
        private String andImg;
        private boolean isDeleted;
        public Update (DynamicAndBoard dynamicAndBoard){
            this.andBoardId = dynamicAndBoard.getAndBoardId();
            this.userId = dynamicAndBoard.getUserId();
            this.andBoardTag = dynamicAndBoard.getAndBoardTag();
            this.andBoardTitle = dynamicAndBoard.getAndBoardTitle();
            this.andBoardContent = dynamicAndBoard.getAndBoardContent();
            this.andImg = dynamicAndBoard.getAndImg();
            this.isDeleted = dynamicAndBoard.isDeleted();
        }
    }

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class findById {
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

        public findById (DynamicAndBoard dynamicAndBoard){
            this.andBoardId = dynamicAndBoard.getAndBoardId();
            this.andId = dynamicAndBoard.getAndId();
            this.userId = dynamicAndBoard.getUserId();
            this.andBoardTag = dynamicAndBoard.getAndBoardTag();
            this.andBoardTitle = dynamicAndBoard.getAndBoardTitle();
            this.andBoardContent = dynamicAndBoard.getAndBoardContent();
            this.andImg = dynamicAndBoard.getAndImg();
            this.publishedAt = dynamicAndBoard.getPublishedAt();
            this.updatedAt = dynamicAndBoard.getUpdatedAt();
            this.andBoardViewCount = dynamicAndBoard.getAndBoardViewCount();
            this.isDeleted = dynamicAndBoard.isDeleted();
        }
    }
}
