package com.fiveis.andcrowd.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicCrowdOrderDetails { // 크라우드 주문내역

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
}