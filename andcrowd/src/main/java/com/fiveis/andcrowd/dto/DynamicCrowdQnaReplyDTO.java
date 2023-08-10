package com.fiveis.andcrowd.dto;

import lombok.*;

import java.time.LocalDateTime;

public class DynamicCrowdQnaReplyDTO {
    @Getter @Setter @ToString @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Find {
        private int crowdId;

        private int qnaReplyId;

        private int crowdQnaId;

        private String qnaReplyContent;

        private LocalDateTime publishedAt;

        private boolean isDeleted;
    }

    @Getter @Setter @ToString @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Save {
        private int crowdId;

        private int crowdQnaId;

        private String qnaReplyContent;
    }

    @Getter @Setter @ToString @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private int crowdId;

        private int crowdQnaId;

        private int qnaReplyId;

        private String qnaReplyContent;
    }
}
