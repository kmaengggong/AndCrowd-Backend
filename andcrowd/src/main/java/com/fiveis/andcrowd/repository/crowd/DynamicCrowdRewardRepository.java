package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdRewardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicCrowdRewardRepository {

    List<DynamicCrowdRewardDTO.FindAllById> findAll(@Param("crowd_id") int crowdId);
    List<DynamicCrowdRewardDTO.FindAllById> findAllNotDeleted(@Param("crowd_id") int crowdId);
    DynamicCrowdRewardDTO.FindAllById findByRewardId(@Param("crowd_id") int crowdId, @Param("reward_id") int rewardId);

    void save(DynamicCrowdRewardDTO.Update dynamicRewardInsertDTO);
    void update(DynamicCrowdRewardDTO.Update dynamicRewardUpdateDTO);
    void deleteByRewardId(@Param("crowd_id") int crowdId, @Param("reward_id") int rewardId);

    void createDynamicCrowdRewardTable(@Param("crowd_id") int crowdId);
    void testCreateDynamicCrowdRewardTable();
    void dropCrowdRewardTable();
    void insertTestData();

}
