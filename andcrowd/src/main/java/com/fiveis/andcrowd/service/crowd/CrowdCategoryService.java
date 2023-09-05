package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdCategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrowdCategoryService {
    List<CrowdCategoryDTO.Find> findAll();

    CrowdCategoryDTO.Find findById(int crowdCategoryId);

    public void save(CrowdCategoryDTO.Save crowdCategoryDTOSave);

    public void update(CrowdCategoryDTO.Update crowdCategoryDTOUpdate);

    public void deleteById(int crowdCategoryId);
}
