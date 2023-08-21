package com.fiveis.andcrowd.entity.crowd;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DynamicCrowdReward { // 리워드 카테고리

    private int rewardId;

    private int crowdId;

    private String rewardTitle;

    private String rewardContent;

    private int rewardAmount;

    private int rewardLimit;

    private boolean isDeleted;
}