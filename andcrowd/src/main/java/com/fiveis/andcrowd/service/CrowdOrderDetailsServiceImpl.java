package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.CrowdOrderDetails;
import com.fiveis.andcrowd.repository.CrowdOrderDetailsJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrowdOrderDetailsServiceImpl implements CrowdOrderDetailsService{

    CrowdOrderDetailsJPARepository crowdOrderDetailsJPARepository;

    @Autowired
    public CrowdOrderDetailsServiceImpl(CrowdOrderDetailsJPARepository crowdOrderDetailsJPARepository){
        this.crowdOrderDetailsJPARepository = crowdOrderDetailsJPARepository;
    }

    @Override
    public List<CrowdOrderDetailsDTO.FindById> findAll() {
        List<CrowdOrderDetails> crowdOrderDetailsList = crowdOrderDetailsJPARepository.findAll();
        List<CrowdOrderDetailsDTO.FindById> findList = new ArrayList<>();
        for(CrowdOrderDetails crowdOrderDetails : crowdOrderDetailsList){
            findList.add(converToCrowdOrderDetailsDTOFind(crowdOrderDetailsList));
        }
        return findList;
    }

    @Override
    public CrowdOrderDetailsDTO.FindById findById(int purchaseId) {

        return null;
    }

    @Override
    public void save(CrowdOrderDetails crowdOrderDetails) {

    }

    @Override
    public void update(CrowdOrderDetailsDTO crowdOrderDetailsDTO) {

    }

    @Override
    public void deleteById(int purchaseId) {

    }
}
