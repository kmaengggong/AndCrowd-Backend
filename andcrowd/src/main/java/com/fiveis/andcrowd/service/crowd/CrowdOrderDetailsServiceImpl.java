package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import com.fiveis.andcrowd.repository.crowd.CrowdOrderDetailsJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrowdOrderDetailsServiceImpl implements CrowdOrderDetailsService{

    CrowdOrderDetailsJPARepository crowdOrderDetailsJPARepository;

    @Autowired
    public CrowdOrderDetailsServiceImpl(CrowdOrderDetailsJPARepository crowdOrderDetailsJPARepository){
        this.crowdOrderDetailsJPARepository = crowdOrderDetailsJPARepository;
    }

    @Override
    public List<CrowdOrderDetailsDTO.FindById> findAll() {//int crowdId) {
        List<CrowdOrderDetails> orderList = crowdOrderDetailsJPARepository.findAll();
        return orderList.stream()
                .map(this::convertToFindByIdDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrowdOrderDetailsDTO.FindById> findById(int purchaseId) {
        Optional<CrowdOrderDetails> crowdOrderDetailsOptional = crowdOrderDetailsJPARepository.findById(purchaseId);
        return crowdOrderDetailsOptional.map(this::convertToFindByIdDTO);
    }

    @Override
    public void save(CrowdOrderDetails crowdOrderDetails) {
        CrowdOrderDetails insertOrder = crowdOrderDetailsJPARepository.save(crowdOrderDetails);
    }

    @Override
    public void update(CrowdOrderDetails crowdOrderDetails) {
        CrowdOrderDetails updateOrder = crowdOrderDetailsJPARepository.findById(crowdOrderDetails.getPurchaseId()).get();
        crowdOrderDetailsJPARepository.save(updateOrder);
    }

    @Override
    public void deleteById(int purchaseId) {
        crowdOrderDetailsJPARepository.deleteById(purchaseId);
    }

    @Override
    public CrowdOrderDetailsDTO.FindById convertToFindByIdDTO(CrowdOrderDetails crowdOrderDetails) {
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
