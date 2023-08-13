package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicRewardDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicRewardService {

    List<DynamicRewardDTO.FindAllById> findAll(int crowdId);

    DynamicRewardDTO.FindAllById findByCrowdId(@Param("crowd_id") int crowdId, @Param("reward_id") int rewardId);

    List<DynamicRewardDTO.FindAllById> findByUserId(@Param("crowd_id") int crowdId, @Param("user_id") int userId);

    void save(DynamicRewardDTO.Update crowdRewardInsertDTO);
    void update(DynamicRewardDTO.Update crowdRewardUpdateDTO);
    void deleteByCrowdRewardId(@Param("crowd_id") int crowdId, @Param("reward_id") int rewardId);
}