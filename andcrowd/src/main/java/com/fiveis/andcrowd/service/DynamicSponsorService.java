package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicSponsorDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicSponsorService {

    List<DynamicSponsorDTO.FindById> findAll(int crowdId);

    DynamicSponsorDTO.FindById findByCrowdId(@Param("crowd_id") int crowdId, @Param("sponsor_id") int sponsorId);

    List<DynamicSponsorDTO.FindById> findByUserId(@Param("crowd_id") int crowdId, @Param("user_id") int userId);

    void save(DynamicSponsorDTO.Update crowdSponsorInsertDTO);
    void update(DynamicSponsorDTO.Update crowdSponsorUpdateDTO);
    void deleteByCrowdSponsorId(@Param("crowd_id") int crowdId, @Param("sponsor_id") int sponsorId);
}