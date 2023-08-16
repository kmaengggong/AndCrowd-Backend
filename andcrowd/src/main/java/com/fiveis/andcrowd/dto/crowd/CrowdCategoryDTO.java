package com.fiveis.andcrowd.dto.crowd;

import com.fiveis.andcrowd.entity.crowd.CrowdCategory;
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

        public static CrowdCategoryDTO.Find convertToCrowdCategoryFindDTO(CrowdCategory crowdCategory) {
            return CrowdCategoryDTO.Find.builder()
                    .crowdCategoryId(crowdCategory.getCrowdCategoryId())
                    .crowdCategoryName(crowdCategory.getCrowdCategoryName())
                    .build();
        }
    }

}
