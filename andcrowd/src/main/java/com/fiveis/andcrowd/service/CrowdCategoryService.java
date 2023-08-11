package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.CrowdCategoryDTO;

import java.util.List;

public interface CrowdCategoryService {
    List<CrowdCategoryDTO.Find> findAll();
    CrowdCategoryDTO.Find findById(int crowdCategoryId);
}
