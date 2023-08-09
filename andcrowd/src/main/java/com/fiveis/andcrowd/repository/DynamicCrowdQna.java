package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicCrowdBoardDTO;
import com.fiveis.andcrowd.dto.DynamicCrowdQnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicCrowdQna {
    void createDynamicCrowdQnaTable(@Param("corwdId") int crowdId);

    List<DynamicCrowdQnaDTO.Find> findAll(int crowdId);

    DynamicCrowdQnaDTO.Find findById(@Param("crowdId") int crowdId, @Param("crowdQnaId") int crowdQnaId);

    void deleteByCrowdQnaId(@Param("crowdId") int crowdId, @Param("crowdQnaId") int crowdQnaId);

    void save(DynamicCrowdQnaDTO.Save dynamicCrowdQnaDTOSave);

    void update(DynamicCrowdQnaDTO.Update dynamicCrowdBoardDTOUpdate);
}
