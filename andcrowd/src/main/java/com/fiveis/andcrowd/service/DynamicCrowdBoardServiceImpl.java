package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicCrowdBoardDTO;
import com.fiveis.andcrowd.repository.DynamicCrowdBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicCrowdBoardServiceImpl implements DynamicCrowdBoardService{

    DynamicCrowdBoardRepository dynamicCrowdBoardRepository;

    @Autowired
    public DynamicCrowdBoardServiceImpl(DynamicCrowdBoardRepository dynamicCrowdBoardRepository){
        this.dynamicCrowdBoardRepository = dynamicCrowdBoardRepository;
    }
    @Override
    public void createDynamicCrowdBoardTable(int crowdId) {
        dynamicCrowdBoardRepository.createDynamicCrowdBoardTable(crowdId);
    }

    @Override
    public List<DynamicCrowdBoardDTO.Find> findAll(int crowdId) {
        return dynamicCrowdBoardRepository.findAll(crowdId);
    }

    @Override
    public DynamicCrowdBoardDTO.Find findById(int crowdId, int crowdBoardId) {
        return dynamicCrowdBoardRepository.findById(crowdId, crowdBoardId);
    }

    @Override
    public void deleteByCrowdBoardId(int crowdId, int crowdBoardId) {
        dynamicCrowdBoardRepository.deleteByCrowdBoardId(crowdId, crowdBoardId);
    }

    @Override
    public void save(DynamicCrowdBoardDTO.Save dynamicCrowdBoardDTOSave) {
        dynamicCrowdBoardRepository.save(dynamicCrowdBoardDTOSave);
    }

    @Override
    public void update(DynamicCrowdBoardDTO.Update dynamicCrowdBoardDTOUpdate) {
        dynamicCrowdBoardRepository.update(dynamicCrowdBoardDTOUpdate);
    }
}
