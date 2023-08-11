package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicSponsorDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicSponsorRepository {

    List<DynamicSponsorDTO.findById> findAll(int crowdId);

    DynamicSponsorDTO.findById findByRewardId(@Param("crowd_id") int crowdId, @Param("sponsor_id") int sponsorId);

    void save(DynamicSponsorDTO.Update dynamicSponsorInsertDTO);

    void update(DynamicSponsorDTO.Update dynamicSponsordUpdateDTO);

    void deleteByRewardId(@Param("crowd_id") int crowdId, @Param("sponsor_id") int sponsorId);

    void createRewardTable();
    void dropRewardTable();
    void insertTestData();
}
