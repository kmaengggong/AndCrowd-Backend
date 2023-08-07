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
    }
}
