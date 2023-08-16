package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdQnaDTO;
import com.fiveis.andcrowd.repository.crowd.DynamicCrowdQnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicCrowdQnaServiceImpl implements DynamicCrowdQnaService {

    DynamicCrowdQnaRepository dynamicCrowdQnaRepository;

    @Autowired
    public DynamicCrowdQnaServiceImpl(DynamicCrowdQnaRepository dynamicCrowdQnaRepository){
        this.dynamicCrowdQnaRepository = dynamicCrowdQnaRepository;
    }


    @Override
    public void createDynamicCrowdQnaTable(int crowdId) {
        dynamicCrowdQnaRepository.createDynamicCrowdQnaTable(crowdId);
    }

    @Override
    public List<DynamicCrowdQnaDTO.Find> findAll(int crowdId) {
        return dynamicCrowdQnaRepository.findAll(crowdId);
    }

    @Override
    public DynamicCrowdQnaDTO.Find findById(int crowdId, int crowdQnaId) {
        return dynamicCrowdQnaRepository.findById(crowdId, crowdQnaId);
    }

    @Override
    public void deleteByCrowdQnaId(int crowdId, int crowdQnaId) {
        dynamicCrowdQnaRepository.deleteByCrowdQnaId(crowdId, crowdQnaId);
    }

    @Override
    public void save(DynamicCrowdQnaDTO.Save dynamicCrowdQnaDTOSave) {
        dynamicCrowdQnaRepository.save(dynamicCrowdQnaDTOSave);
    }

    @Override
    public void update(DynamicCrowdQnaDTO.Update dynamicCrowdBoardDTOUpdate) {
        dynamicCrowdQnaRepository.update(dynamicCrowdBoardDTOUpdate);
    }
}
