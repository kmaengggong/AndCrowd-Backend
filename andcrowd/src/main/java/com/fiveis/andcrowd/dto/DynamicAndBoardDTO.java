package com.fiveis.andcrowd.dto;
import lombok.*;

import java.time.LocalDateTime;

public class DynamicAndBoardDTO {

    @Getter @Setter @ToString @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Find {
        private int andId;

        private int andBoardId;

        private int andBoardTag;

        private String andBoardTitle;

        private String andBoardContent;

        private String andImg;

        private LocalDateTime publishedAt;

        private LocalDateTime updatedAt;

        private int viewCount;

        private boolean isDeleted;
    }

    //모임 게시판 업데이트
    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class Update {
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

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class FindById {
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

}
