package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.CrowdOrderDetails;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

public class CrowdOrderDetailsDTO { // findAll, findById, update, save, delete
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class findById {
        private int purchaseId;
        private int userId;
        private int crowdId;
        private int rewardId;
        private int sponsorId;
        private String purchaseName;
        private String purchasePhone;
        private String purchaseAddress;
        private LocalDateTime purchaseDate;
        private String purchaseStatus;
        private boolean isDeleted;

        public findById(CrowdOrderDetails crowdOrderDetails){
            this.purchaseId = crowdOrderDetails.getPurchaseId();
            this.userId = crowdOrderDetails.getUserId();
            this.crowdId = crowdOrderDetails.getCrowdId();
            this.rewardId = crowdOrderDetails.getRewardId();
            this.sponsorId = crowdOrderDetails.getSponsorId();
            this.purchaseName = crowdOrderDetails.getPurchaseName();
            this.purchasePhone = crowdOrderDetails.getPurchasePhone();
            this.purchaseAddress = crowdOrderDetails.getPurchaseAddress();
            this.purchaseDate = crowdOrderDetails.getPurchaseDate();
            this.purchaseStatus = crowdOrderDetails.getPurchaseStatus();
            this.isDeleted = crowdOrderDetails.isDeleted();
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
        private int sponsorId;
        private String purchaseName;
        private String purchasePhone;
        private String purchaseAddress;
        private LocalDateTime purchaseDate;
        private String purchaseStatus;
        private boolean isDeleted;

        public Update(CrowdOrderDetails crowdOrderDetails){
            this.purchaseId = crowdOrderDetails.getPurchaseId();
            this.userId = crowdOrderDetails.getUserId();
            this.crowdId = crowdOrderDetails.getCrowdId();
            this.rewardId = crowdOrderDetails.getRewardId();
            this.sponsorId = crowdOrderDetails.getSponsorId();
            this.purchaseName = crowdOrderDetails.getPurchaseName();
            this.purchasePhone = crowdOrderDetails.getPurchasePhone();
            this.purchaseAddress = crowdOrderDetails.getPurchaseAddress();
            this.purchaseDate = crowdOrderDetails.getPurchaseDate();
            this.purchaseStatus = crowdOrderDetails.getPurchaseStatus();
            this.isDeleted = crowdOrderDetails.isDeleted();
        }
    }
}
