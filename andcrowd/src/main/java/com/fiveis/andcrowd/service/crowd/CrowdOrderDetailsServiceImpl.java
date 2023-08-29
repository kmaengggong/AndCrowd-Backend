package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import com.fiveis.andcrowd.repository.crowd.CrowdOrderDetailsJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    } // 모든 결제내역을 조회하는 메서드

    @Override
    public Optional<CrowdOrderDetailsDTO.FindById> findById(int purchaseId) {
        Optional<CrowdOrderDetails> crowdOrderDetailsOptional = crowdOrderDetailsJPARepository.findById(purchaseId);
        return crowdOrderDetailsOptional.map(this::convertToFindByIdDTO);
    } // 특정 주문을 ID로 조회하는 메서드

    @Override
    public void save(CrowdOrderDetails crowdOrderDetails) {
        CrowdOrderDetails insertOrder = crowdOrderDetailsJPARepository.save(crowdOrderDetails);
    } // 주문내역 저장 메서드

    @Override
    public void update(CrowdOrderDetailsDTO.Update updateDTO) { //CrowdOrderDetails crowdOrderDetails) {
        Optional<CrowdOrderDetails> orderOptional = crowdOrderDetailsJPARepository.findById(updateDTO.getPurchaseId());
        orderOptional.ifPresent(updateOrder -> {
            // 필요한 필드 업데이트
            updateOrder.setPurchaseName(updateDTO.getPurchaseName());
            updateOrder.setPurchaseAddress(updateDTO.getPurchaseAddress());
            updateOrder.setPurchasePhone(updateDTO.getPurchasePhone());
            crowdOrderDetailsJPARepository.save(updateOrder);
        });
//        CrowdOrderDetails updateOrder = crowdOrderDetailsJPARepository.findById(crowdOrderDetails.getPurchaseId()).get();
//        crowdOrderDetailsJPARepository.save(crowdOrderDetails);
    } // 주문내역 수정 메서드

    @Override
    @Transactional
    public void deleteById(int purchaseId) {
        Optional<CrowdOrderDetails> orderOptional = crowdOrderDetailsJPARepository.findById(purchaseId);

        if(orderOptional.isPresent()) {
            CrowdOrderDetails crowdOrderDetails = orderOptional.get();
            crowdOrderDetails.setDeleted(true);
            crowdOrderDetailsJPARepository.save(crowdOrderDetails);
        }
    } // 주문내역 삭제 메서드

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
