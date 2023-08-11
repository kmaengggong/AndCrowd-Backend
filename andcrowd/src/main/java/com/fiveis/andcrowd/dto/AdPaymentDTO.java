package com.fiveis.andcrowd.dto;

import lombok.*;

import java.time.LocalDateTime;

public class AdPaymentDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Find{
        private int adPaymentId;
        private int adId;
        private int userId;
        private int projectId;
        private int projectType;
        private LocalDateTime purchasedAt;
        private LocalDateTime expiredAt;
        private int adPaymentStatus;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        private int adPaymentId;
        private LocalDateTime expiredAt;
        private int adPaymentStatus;
    }
}
