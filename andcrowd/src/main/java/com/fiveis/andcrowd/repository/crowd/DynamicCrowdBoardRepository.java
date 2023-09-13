package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicCrowdBoardRepository {

    List<DynamicCrowdBoardDTO.Find> findAll(int crowdId);

    List<DynamicCrowdBoardDTO.Find> findAllByIsDeletedFalse(int crowdId);

    DynamicCrowdBoardDTO.Find findById(@Param("crowdId") int crowdId, @Param("crowdBoardId") int crowdBoardId);

    void save(DynamicCrowdBoardDTO.Save dynamicCrowdBoardDTOSave);

    void update(DynamicCrowdBoardDTO.Update dynamicCrowdBoardDTOUpdate);

    void deleteByCrowdBoardId(/*Map<String, Integer> params*/@Param("crowdId") int crowdId, @Param("crowdBoardId") int crowdBoardId);

    //CrowdService의 deleteAll기능 추가
    void deleteByCrowdId(@Param("crowdId") int crowdId);

    void createDynamicCrowdBoardTable(int crowdId);
}
