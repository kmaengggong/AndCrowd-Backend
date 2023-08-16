package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import com.fiveis.andcrowd.repository.crowd.CrowdOrderDetailsJPARepository;
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

        for (CrowdOrderDetails crowdOrderDetails : crowdOrderDetailsList) {
            CrowdOrderDetailsDTO.FindById findById = convertToFindById(crowdOrderDetails);
            findList.add(findById);
        }

        return findList;
    }

    @Override
    public CrowdOrderDetailsDTO.FindById findById(int purchaseId) {
//        Optional<CrowdOrderDetails> crowdOrderDetailsOptional = crowdOrderDetailsJPARepository.findById(purchaseId);
        if(crowdOrderDetailsJPARepository.findById(purchaseId).isEmpty()) return null;
        return convertToFindById(crowdOrderDetailsJPARepository.findById(purchaseId).get());
    }

    @Override
    public void save(CrowdOrderDetails crowdOrderDetails) {
        CrowdOrderDetails saveCrowdOrder = crowdOrderDetailsJPARepository.save(crowdOrderDetails);
    }

    @Override
    public void update(CrowdOrderDetails crowdOrderDetails) {
        crowdOrderDetailsJPARepository.save(crowdOrderDetails);
    }

    @Override
    public void deleteById(int purchaseId) {
        crowdOrderDetailsJPARepository.deleteById(purchaseId);
    }

    private CrowdOrderDetailsDTO.FindById convertToFindById(CrowdOrderDetails crowdOrderDetails) {
        return CrowdOrderDetailsDTO.FindById.builder()
                .purchaseId(crowdOrderDetails.getPurchaseId())
                .userId(crowdOrderDetails.getUserId())
                .crowdId(crowdOrderDetails.getCrowdId())
                .rewardId(crowdOrderDetails.getRewardId())
                .sponsorId(crowdOrderDetails.getSponsorId())
                .purchaseName(crowdOrderDetails.getPurchaseName())
                .purchasePhone(crowdOrderDetails.getPurchasePhone())
                .purchaseAddress(crowdOrderDetails.getPurchaseAddress())
                .purchaseDate(crowdOrderDetails.getPurchaseDate())
                .purchaseStatus(crowdOrderDetails.getPurchaseStatus())
                .isDeleted(crowdOrderDetails.isDeleted())
                .build();
    }
}
