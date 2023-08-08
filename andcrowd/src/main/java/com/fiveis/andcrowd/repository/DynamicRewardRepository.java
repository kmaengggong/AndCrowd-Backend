package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicRewardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DynamicRewardRepository {

    List<DynamicRewardDTO> findAll();

    DynamicRewardDTO findById(int rewardId);

    void save(DynamicRewardDTO dynamicRewardDTO);

    void update(DynamicRewardDTO.Update rewardList);

}
