package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.Crowd;
import com.fiveis.andcrowd.entity.DynamicReward;
import lombok.*;

@Getter @Setter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
public class DynamicRewardDTO { // FindAllByUserId, update, save, delete

    public static class FindAllByUserId { // 크라우드 리워드 조회
        private int rewardId;
        private int crowdId;
        private String rewardTitle;
        private String rewardContent;
        private int rewardAmount;
        private int rewardLimit;

        public FindAllByUserId(DynamicReward dynamicReward){
            this.rewardId = dynamicReward.getRewardId();
            this.crowdId = dynamicReward.getCrowdId();
            this.rewardTitle = dynamicReward.getRewardTitle();
            this.rewardContent = dynamicReward.getRewardContent();
            this.rewardAmount = dynamicReward.getRewardAmount();
            this.rewardLimit = dynamicReward.getRewardLimit();
        }
    }

    public static class Update{ // 크라우드 리워드 업데이트
        private int rewardId;
        private String rewardTitle;
        private String rewardContent;
        private int rewardAmount;
        private int rewardLimit;

        public Update(DynamicReward dynamicReward){
            this.rewardId = dynamicReward.getRewardId();
            this.rewardTitle = dynamicReward.getRewardTitle();
            this.rewardContent = dynamicReward.getRewardContent();
            this.rewardAmount = dynamicReward.getRewardAmount();
            this.rewardLimit = dynamicReward.getRewardLimit();
        }
    }

}
