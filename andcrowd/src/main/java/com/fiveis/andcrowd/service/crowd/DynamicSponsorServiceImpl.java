package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicSponsorDTO;
import com.fiveis.andcrowd.repository.crowd.DynamicSponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicSponsorServiceImpl implements DynamicSponsorService{

    DynamicSponsorRepository dynamicSponsorRepository;

    @Autowired
    public DynamicSponsorServiceImpl(DynamicSponsorRepository dynamicSponsorRepository){
        this.dynamicSponsorRepository = dynamicSponsorRepository;
    }

    @Override
    public List<DynamicSponsorDTO.FindById> findAll(int crowdId) {
        return dynamicSponsorRepository.findAll(crowdId);
    }

    @Override
    public DynamicSponsorDTO.FindById findByCrowdId(int crowdId, int sponsorId) {
        return dynamicSponsorRepository.findBySponsorId(crowdId, sponsorId);
    }

    @Override
    public List<DynamicSponsorDTO.FindById> findByUserId(int crowdId, int userId) {
        return dynamicSponsorRepository.findByUserId(crowdId, userId);
    }

    @Override
    public void save(DynamicSponsorDTO.Update crowdSponsorInsertDTO) {
        dynamicSponsorRepository.save(crowdSponsorInsertDTO);
    }

    @Override
    public void update(DynamicSponsorDTO.Update crowdSponsorUpdateDTO) {
        dynamicSponsorRepository.update(crowdSponsorUpdateDTO);
    }

    @Override
    public void deleteByCrowdSponsorId(int crowdId, int sponsorId) {
        dynamicSponsorRepository.deleteBySponsorId(crowdId, sponsorId);
    }
}