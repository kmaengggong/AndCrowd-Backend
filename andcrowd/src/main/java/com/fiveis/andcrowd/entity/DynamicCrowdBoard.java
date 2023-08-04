package com.fiveis.andcrowd.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DynamicCrowdBoard {

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
