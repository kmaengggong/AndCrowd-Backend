package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.DynamicSponsor;

public class DynamicSponsorDTO { // find(findAll, findById), Update

    public static class findById {
        private int sponsorId;
        private int crowdId;
        private int purchaseId;

        public findById(DynamicSponsor dynamicSponsor){
            this.sponsorId = dynamicSponsor.getSponsorId();
            this.crowdId = dynamicSponsor.getCrowdId();
            this.purchaseId = dynamicSponsor.getPurchaseId();
        }
    }

    public static class Update {
        private int sponsorId;
        private int crowdId;
        private int purchaseId;

        public Update(DynamicSponsor dynamicSponsor) {
            this.sponsorId = dynamicSponsor.getSponsorId();
            this.crowdId = dynamicSponsor.getCrowdId();
            this.purchaseId = dynamicSponsor.getPurchaseId();
        }
    }

}
