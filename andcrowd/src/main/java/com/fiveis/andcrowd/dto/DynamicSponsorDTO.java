package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.DynamicSponsor;
import lombok.*;

public class DynamicSponsorDTO { // find(findAll, findById), Update
    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class findById {
        private int sponsorId;
        private int crowdId;
        private int purchaseId;
    }

    @Getter @Setter
    @Builder @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private int sponsorId;
        private int crowdId;
        private int purchaseId;
    }

}
