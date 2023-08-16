package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicSponsorDTO;
import com.fiveis.andcrowd.repository.crowd.DynamicCrowdSponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicCrowdSponsorServiceImpl implements DynamicCrowdSponsorService {

    DynamicCrowdSponsorRepository dynamicCrowdSponsorRepository;

    @Autowired
    public DynamicCrowdSponsorServiceImpl(DynamicCrowdSponsorRepository dynamicCrowdSponsorRepository){
        this.dynamicCrowdSponsorRepository = dynamicCrowdSponsorRepository;
    }

    @Override
    public List<DynamicSponsorDTO.FindById> findAll(int crowdId) {
        return dynamicCrowdSponsorRepository.findAll(crowdId);
    }

    @Override
    public DynamicSponsorDTO.FindById findByCrowdId(int crowdId, int sponsorId) {
        return dynamicCrowdSponsorRepository.findBySponsorId(crowdId, sponsorId);
    }

    @Override
    public List<DynamicSponsorDTO.FindById> findByUserId(int crowdId, int userId) {
        return dynamicCrowdSponsorRepository.findByUserId(crowdId, userId);
    }

    @Override
    public void save(DynamicSponsorDTO.Update crowdSponsorInsertDTO) {
        dynamicCrowdSponsorRepository.save(crowdSponsorInsertDTO);
    }

    @Override
    public void update(DynamicSponsorDTO.Update crowdSponsorUpdateDTO) {
        dynamicCrowdSponsorRepository.update(crowdSponsorUpdateDTO);
    }

    @Override
    public void deleteByCrowdSponsorId(int crowdId, int sponsorId) {
        dynamicCrowdSponsorRepository.deleteBySponsorId(crowdId, sponsorId);
    }
}