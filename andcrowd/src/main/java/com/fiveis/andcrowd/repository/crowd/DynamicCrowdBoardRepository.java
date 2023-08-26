package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicCrowdBoardRepository {

    void createDynamicCrowdBoardTable(@Param("crowdId") int crowdId);

    List<DynamicCrowdBoardDTO.Find> findAll(int crowdId);

    List<DynamicCrowdBoardDTO.Find> findAllByIsDeletedFalse(int crowdId);

    DynamicCrowdBoardDTO.Find findById(@Param("crowdId") int crowdId, @Param("crowdBoardId") int crowdBoardId);

    void deleteByCrowdBoardId(/*Map<String, Integer> params*/@Param("crowdId") int crowdId, @Param("crowdBoardId") int crowdBoardId);

    void save(DynamicCrowdBoardDTO.Save dynamicCrowdBoardDTOSave);

    void update(DynamicCrowdBoardDTO.Update dynamicCrowdBoardDTOUpdate);

<<<<<<< HEAD
    //CrowdService의 delete메서드에서 사용할 전체삭제기능 추가
    void deleteByCrowdId(@Param("crowdId") int crowdId);
=======
>>>>>>> 1887d1ec2c8b7acd8df9420e534639f5c68b12f2
}
