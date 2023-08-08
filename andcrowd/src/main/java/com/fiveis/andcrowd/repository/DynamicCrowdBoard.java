package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicCrowdBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicCrowdBoard {

    void createDynamicCrowdBoardTable(int crowdId);

    List<DynamicCrowdBoardDTO.Find> findAll(int crowdId);

    DynamicCrowdBoardDTO.Find findById(Map<String, Integer> params);

    void deleteByCrowdBoardId(Map<String, Integer> params);

    void save(DynamicCrowdBoardDTO.Save dynamicCrowdBoardDTOSave);

    void update(DynamicCrowdBoardDTO.Update dynamicCrowdBoardDTOUpdate);
}
