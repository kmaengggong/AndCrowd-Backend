package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.CrowdCategory;
import lombok.*;

public class CrowdCategoryDTO {

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Find {
        private int crowdCategoryId;
        private String crowdCategoryName;

        public static CrowdCategoryDTO.Find fromEntity(CrowdCategory crowdCategory) {
            return CrowdCategoryDTO.Find.builder()
                    .crowdCategoryId(crowdCategory.getCrowdCategoryId())
                    .crowdCategoryName(crowdCategory.getCrowdCategoryName())
                    .build();
        }
    }

}
