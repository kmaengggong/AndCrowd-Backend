package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicCrowdBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicCrowdBoard {

    void createDynamicCrowdBoardTable(@Param("crowdId") int crowdId);

    List<DynamicCrowdBoardDTO.Find> findAll(int crowdId);

    DynamicCrowdBoardDTO.Find findById(@Param("crowdId") int crowdId, @Param("crowdBoardId") int crowdBoardId);

    void deleteByCrowdBoardId(/*Map<String, Integer> params*/@Param("crowdId") int crowdId, @Param("crowdBoardId") int crowdBoardId);

    void save(DynamicCrowdBoardDTO.Save dynamicCrowdBoardDTOSave);

    void update(DynamicCrowdBoardDTO.Update dynamicCrowdBoardDTOUpdate);
}
