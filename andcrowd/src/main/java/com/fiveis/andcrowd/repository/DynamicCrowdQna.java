package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicCrowdBoardDTO;
import com.fiveis.andcrowd.dto.DynamicCrowdQnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicCrowdQna {

    void createDynamicCrowdQnaTable(int crowdId);

    List<DynamicCrowdQnaDTO.Find> findAll(int crowdId);

    DynamicCrowdQnaDTO.Find findById(Map<String, Integer> params);

    void deleteByCrowdQnaId(Map<String, Integer> params);

    void save(DynamicCrowdQnaDTO.Save dynamicCrowdQnaDTOSave);

    void update(DynamicCrowdQnaDTO.Update dynamicCrowdBoardDTOUpdate);
}
