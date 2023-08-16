package com.fiveis.andcrowd.entity.crowd;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicCrowdQnaReply {

    private int qnaReplyId;

    private int crowdQnaId;

    private String qnaReplyContent;

    private LocalDateTime publishedAt;

    private boolean isDeleted;
}