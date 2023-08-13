package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.CrowdDTO;
import com.fiveis.andcrowd.entity.Crowd;
import com.fiveis.andcrowd.repository.CrowdJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CrowdServiceImpl implements CrowdService {

    CrowdJPARepository crowdJPARepository;

    @Autowired
    public CrowdServiceImpl(CrowdJPARepository crowdJPARepository){
        this.crowdJPARepository = crowdJPARepository;
    }

    @Override
    public Optional<CrowdDTO.FindById> findByCrowdId(int crowdId) {
        Optional<Crowd> crowdOptional = crowdJPARepository.findById(crowdId);
        return crowdOptional.map(this::convertToAndFindByCrowdId);
    }

    @Override
    public List<CrowdDTO.FindAllByUserId> findAllByUserIdList(int userId) {
        return crowdJPARepository.findAllByUserId(userId);
    }

    @Override
    public List<CrowdDTO.FindById> findAll() {
        List<Crowd> crowdList = crowdJPARepository.findAll();
        List<CrowdDTO.FindById> findByIdList = new ArrayList<>();

        for(Crowd crowd : crowdList){
            CrowdDTO.FindById result = convertToAndFindByCrowdId(crowd);
            findByIdList.add(result);
        }
        return findByIdList;
    }

    @Override
    public void deleteByCrowdId(int crowdId) {
        crowdJPARepository.deleteById(crowdId);
    }

    @Override
    public void save(Crowd crowd) {
        crowdJPARepository.save(crowd);
    }

    @Override
    public CrowdDTO.FindById convertToAndFindByCrowdId(Crowd crowd) {
        return null;
    }

    @Override
    public void update(Crowd crowd) {
        Crowd updatedCrowd = crowdJPARepository.findById(crowd.getCrowdId()).get();
        updatedCrowd.setCrowdTitle(crowd.getCrowdTitle());
        updatedCrowd.setCrowdContent(crowd.getCrowdContent());
        crowdJPARepository.save(updatedCrowd);
    }
}