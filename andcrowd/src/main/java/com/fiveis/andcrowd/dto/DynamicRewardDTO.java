package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.Crowd;
import com.fiveis.andcrowd.entity.DynamicReward;
import lombok.*;

public class DynamicRewardDTO { // FindAllByUserId, update, save, delete
    @Getter @Setter
    @Builder @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindAllById { // 크라우드 리워드 조회
        private int rewardId;
        private int crowdId;
        private String rewardTitle;
        private String rewardContent;
        private int rewardAmount;
        private int rewardLimit;
        private boolean isDeleted;
    }

    @Getter @Setter
    @Builder @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update{ // 크라우드 리워드 업데이트
        private int rewardId;
        private String rewardTitle;
        private String rewardContent;
        private int rewardAmount;
        private int rewardLimit;
    }

}
