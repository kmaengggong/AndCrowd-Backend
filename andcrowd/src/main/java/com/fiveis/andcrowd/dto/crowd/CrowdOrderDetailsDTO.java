package com.fiveis.andcrowd.dto.crowd;

import com.fiveis.andcrowd.entity.crowd.Crowd;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import lombok.*;

import java.time.LocalDateTime;

public class CrowdOrderDetailsDTO { // findAll, findById, update, save, delete
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class FindById {
        private int purchaseId;
        private int userId;
        private int crowdId;
        private int rewardId;
        private String rewardName;
        private String merchantUid;
        private String purchaseName;
        private String purchasePhone;
        private String purchaseAddress;
        private LocalDateTime purchaseDate;
        private String purchaseStatus;
        private boolean isDeleted;

        public static CrowdOrderDetailsDTO.FindById convertToFindByIdDTO(CrowdOrderDetails crowdOrderDetails){
            return FindById.builder()
                    .purchaseId(crowdOrderDetails.getPurchaseId())
                    .userId(crowdOrderDetails.getUserId())
                    .crowdId(crowdOrderDetails.getCrowdId())
                    .rewardId(crowdOrderDetails.getRewardId())
                    .rewardName(crowdOrderDetails.getRewardName())
                    .merchantUid(crowdOrderDetails.getMerchantUid())
                    .purchaseName(crowdOrderDetails.getPurchaseName())
                    .purchasePhone(crowdOrderDetails.getPurchasePhone())
                    .purchaseAddress(crowdOrderDetails.getPurchaseAddress())
                    .purchaseDate(crowdOrderDetails.getPurchaseDate())
                    .purchaseStatus(crowdOrderDetails.getPurchaseStatus())
                    .isDeleted(crowdOrderDetails.isDeleted())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class Update {
        private int purchaseId;
        private int userId;
        private int crowdId;
        private int rewardId;
        private String rewardName;
        private String merchantUid;
        private String purchaseName;
        private String purchasePhone;
        private String purchaseAddress;
        private LocalDateTime purchaseDate;
        private String purchaseStatus;
        private boolean isDeleted;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class rewardCounts {
        private int rewardId; // 리워드ID
        private int rewardCounts; // 해당 리워드의 판매량
        private int rewardAmount; // 리워드 가격
        private int rewardSale; // 해당 리워드의 총 판매액
    }
}
