package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicRewardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicRewardRepository {

    List<DynamicRewardDTO.FindAllById> findAll(int crowdId);

    DynamicRewardDTO.FindAllById findByRewardId(@Param("crowd_id") int crowdId, @Param("reward_id") int rewardId);

    List<DynamicRewardDTO.FindAllById> findByUserId(@Param("crowd_id") int crowdId, @Param("user_id") int userId);

    void save(DynamicRewardDTO.Update dynamicRewardInsertDTO);

    void update(DynamicRewardDTO.Update dynamicRewardUpdateDTO);

    void deleteByRewardId(@Param("crowd_id") int crowdId, @Param("reward_id") int rewardId);

    void createRewardTable();
    void dropRewardTable();
    void insertTestData();

}
