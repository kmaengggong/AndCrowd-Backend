package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicSponsorDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicSponsorRepository {

    List<DynamicSponsorDTO.FindById> findAll(int crowdId);

    DynamicSponsorDTO.FindById findBySponsorId(@Param("crowd_id") int crowdId, @Param("sponsor_id") int sponsorId);

    List<DynamicSponsorDTO.FindById> findByUserId(@Param("crowd_id") int crowdId, @Param("user_id") int userId);

    void save(DynamicSponsorDTO.Update dynamicSponsorInsertDTO);

    void update(DynamicSponsorDTO.Update dynamicSponsorUpdateDTO);

    void deleteBySponsorId(@Param("crowd_id") int crowdId, @Param("sponsor_id") int sponsorId);

    void createSponsorTable();
    void dropSponsorTable();
    void insertTestData();
}