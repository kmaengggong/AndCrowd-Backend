package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicCrowdBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface DynamicCrowdBoardRepository {

    void createDynamicCrowdBoardTable(@Param("crowdId") int crowdId);

    Page<DynamicCrowdBoardDTO.Find> findAll(int crowdId, Pageable pageable);

    DynamicCrowdBoardDTO.Find findById(@Param("crowdId") int crowdId, @Param("crowdBoardId") int crowdBoardId);

    void deleteByCrowdBoardId(/*Map<String, Integer> params*/@Param("crowdId") int crowdId, @Param("crowdBoardId") int crowdBoardId);

    void save(DynamicCrowdBoardDTO.Save dynamicCrowdBoardDTOSave);

    void update(DynamicCrowdBoardDTO.Update dynamicCrowdBoardDTOUpdate);
}
