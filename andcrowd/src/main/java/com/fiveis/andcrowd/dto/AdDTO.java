package com.fiveis.andcrowd.dto;

import lombok.*;

public class AdDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Find{
        private int adId;
        private String adName;
        private int adPrice;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        private int adId;
        private String adName;
        private int adPrice;
    }
}
