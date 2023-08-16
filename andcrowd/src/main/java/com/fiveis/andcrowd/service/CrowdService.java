package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.CrowdDTO;
import com.fiveis.andcrowd.entity.Crowd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CrowdService {

    Optional<CrowdDTO.FindById> findByCrowdId(int crowdId);

    List<CrowdDTO.FindAllByUserId> findAllByUserIdList(int userId);

    List<CrowdDTO.FindById> findAll();

    void deleteByCrowdId(int crowdId);

    void save(Crowd crowd);

    public CrowdDTO.FindById convertToAndFindByCrowdId(Crowd crowd);

    void update(Crowd crowd);
}