package com.fiveis.andcrowd.entity;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DynamicReward { // 리워드 카테고리

    private int rewardId;

    private int crowdId;

    private String rewardTitle;

    private String rewardContent;

    private int rewardAmount;

    private int rewardLimit;
}