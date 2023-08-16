package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicRewardDTO;
import com.fiveis.andcrowd.repository.crowd.DynamicCrowdRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicCrowdRewardServiceImpl implements DynamicCrowdRewardService {

    DynamicCrowdRewardRepository dynamicCrowdRewardRepository;

    @Autowired
    public DynamicCrowdRewardServiceImpl(DynamicCrowdRewardRepository dynamicCrowdRewardRepository){
        this.dynamicCrowdRewardRepository = dynamicCrowdRewardRepository;
    }

    @Override
    public List<DynamicRewardDTO.FindAllById> findAll(int crowdId) {
        return dynamicCrowdRewardRepository.findAll(crowdId);
    }

    @Override
    public DynamicRewardDTO.FindAllById findByCrowdId(int crowdId, int rewardId) {
        return dynamicCrowdRewardRepository.findByRewardId(crowdId, rewardId);
    }

    @Override
    public List<DynamicRewardDTO.FindAllById> findByUserId(int crowdId, int userId) {
        return dynamicCrowdRewardRepository.findByUserId(crowdId, userId);
    }

    @Override
    public void save(DynamicRewardDTO.Update crowdRewardInsertDTO) {
        dynamicCrowdRewardRepository.save(crowdRewardInsertDTO);
    }

    @Override
    public void update(DynamicRewardDTO.Update crowdRewardUpdateDTO) {
        dynamicCrowdRewardRepository.update(crowdRewardUpdateDTO);
    }

    @Override
    public void deleteByCrowdRewardId(int crowdId, int rewardId) {
        dynamicCrowdRewardRepository.deleteByRewardId(crowdId, rewardId);
    }
}