package com.fiveis.andcrowd.dto;

import lombok.*;

@Getter
@Setter
public class CrowdCategoryDTO {
    private int crowdCategoryId;
    private String crowdCategoryName;

    public CrowdCategoryDTO(int crowdCategoryId, String crowdCategoryName){
        this.crowdCategoryId = crowdCategoryId;
        this.crowdCategoryName = crowdCategoryName;
    }

}
