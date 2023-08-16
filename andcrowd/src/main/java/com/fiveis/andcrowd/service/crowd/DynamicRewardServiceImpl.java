package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicRewardDTO;
import com.fiveis.andcrowd.repository.crowd.DynamicRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicRewardServiceImpl implements DynamicRewardService{

    DynamicRewardRepository dynamicRewardRepository;

    @Autowired
    public DynamicRewardServiceImpl(DynamicRewardRepository dynamicRewardRepository){
        this.dynamicRewardRepository = dynamicRewardRepository;
    }

    @Override
    public List<DynamicRewardDTO.FindAllById> findAll(int crowdId) {
        return dynamicRewardRepository.findAll(crowdId);
    }

    @Override
    public DynamicRewardDTO.FindAllById findByCrowdId(int crowdId, int rewardId) {
        return dynamicRewardRepository.findByRewardId(crowdId, rewardId);
    }

    @Override
    public List<DynamicRewardDTO.FindAllById> findByUserId(int crowdId, int userId) {
        return dynamicRewardRepository.findByUserId(crowdId, userId);
    }

    @Override
    public void save(DynamicRewardDTO.Update crowdRewardInsertDTO) {
        dynamicRewardRepository.save(crowdRewardInsertDTO);
    }

    @Override
    public void update(DynamicRewardDTO.Update crowdRewardUpdateDTO) {
        dynamicRewardRepository.update(crowdRewardUpdateDTO);
    }

    @Override
    public void deleteByCrowdRewardId(int crowdId, int rewardId) {
        dynamicRewardRepository.deleteByRewardId(crowdId, rewardId);
    }
}