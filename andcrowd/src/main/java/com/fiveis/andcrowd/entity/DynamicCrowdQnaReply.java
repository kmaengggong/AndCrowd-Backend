package com.fiveis.andcrowd.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicCrowdQnaReply {

    private int crowdReplyId;

    private int crowdQnAId;

    private String crowdReplyContent;

    private LocalDateTime publishedAt;

    private boolean isDeleted;
}