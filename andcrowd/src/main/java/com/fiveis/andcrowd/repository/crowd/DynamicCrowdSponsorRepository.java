package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdSponsorDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicCrowdSponsorRepository {

    List<DynamicCrowdSponsorDTO.FindById> findAll(@Param("crowd_id") int crowdId);
    List<DynamicCrowdSponsorDTO.FindById> findAllNotDeleted(@Param("crowd_id") int crowdId);
    DynamicCrowdSponsorDTO.FindById findBySponsorId(@Param("crowd_id") int crowdId, @Param("sponsor_id") int sponsorId);

    void save(DynamicCrowdSponsorDTO.Update dynamicSponsorInsertDTO);
    void update(DynamicCrowdSponsorDTO.Update dynamicSponsorUpdateDTO);
    void deleteBySponsorId(@Param("crowd_id") int crowdId, @Param("sponsor_id") int sponsorId);

    void createDynamicCrowdSponsorTable(@Param("crowd_id") int crowdId);
    void testCreateDynamicCrowdSponsorTable();
    void dropCrowdSponsorTable();
    void insertTestData();
}
