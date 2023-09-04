package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdQnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicCrowdQnaRepository {
    void createDynamicCrowdQnaTable(@Param("crowdId") int crowdId);

    List<DynamicCrowdQnaDTO.Find> findAll(int crowdId);

    List<DynamicCrowdQnaDTO.Find> findAllByIsDeletedFalse(int crowdId);

    DynamicCrowdQnaDTO.Find findById(@Param("crowdId") int crowdId, @Param("crowdQnaId") int crowdQnaId);

    void deleteByCrowdQnaId(@Param("crowdId") int crowdId, @Param("crowdQnaId") int crowdQnaId);

    void save(DynamicCrowdQnaDTO.Save dynamicCrowdQnaDTOSave);

    void update(DynamicCrowdQnaDTO.Update dynamicCrowdBoardDTOUpdate);

    void deleteAll(@Param("crowdId") int crowdId);
}
