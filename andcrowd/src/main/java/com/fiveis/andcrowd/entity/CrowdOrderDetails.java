package com.fiveis.andcrowd.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CrowdOrderDetails { // 크라우드 주문내역

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int crowdId;

    @Column(nullable = false)
    private int rewardId;

    @Column(nullable = false)
    private int sponsorId;

    @Column(nullable = false)
    private String purchaseName;

    @Column(nullable = false)
    private String purchasePhone;

    @Column(nullable = false)
    private String purchaseAddress;

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private String purchaseStatus;

    @Column(nullable = false)
    private boolean isDeleted;
}