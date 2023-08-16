package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdCategoryDTO;

import java.util.List;

public interface CrowdCategoryService {
    List<CrowdCategoryDTO.Find> findAll();
    CrowdCategoryDTO.Find findById(int crowdCategoryId);
}
