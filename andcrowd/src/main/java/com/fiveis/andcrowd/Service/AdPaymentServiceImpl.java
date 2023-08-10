package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.AdPaymentDTO;
import com.fiveis.andcrowd.entity.AdPayment;
import com.fiveis.andcrowd.repository.AdPaymentJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdPaymentServiceImpl implements AdPaymentService{
    private static AdPaymentJPARepository adPaymentJPARepository;

    @Autowired
    public AdPaymentServiceImpl(AdPaymentJPARepository adPaymentJPARepository){
        this.adPaymentJPARepository = adPaymentJPARepository;
    }

    @Override
    public List<AdPaymentDTO.Find> findAll() {
        List<AdPayment> adPaymentList = adPaymentJPARepository.findAll();
        List<AdPaymentDTO.Find> findList = new ArrayList<>();
        for(AdPayment adPayment : adPaymentList){
            findList.add(converToAdPaymentDTOFind(adPayment));
        }
        return findList;
    }

    @Override
    public AdPaymentDTO.Find findById(int adPaymentId) {
        if(adPaymentJPARepository.findById(adPaymentId).isEmpty()) return null;
        return converToAdPaymentDTOFind(adPaymentJPARepository.findById(adPaymentId).get());
    }

    @Override
    public void save(AdPayment adPayment) {
        adPaymentJPARepository.save(adPayment);
    }

    @Override
    public void update(AdPaymentDTO.Update update) {
        AdPayment adPayment = adPaymentJPARepository.findById(update.getAdPaymentId()).get();
        if(update.getExpiredAt() != null){
            adPayment.setExpiredAt(update.getExpiredAt());
        }

        // 변경사항이 없을 경우 -1을 넣어야 함
        if(update.getAdPaymentStatus() != -1){
            adPayment.setAdPaymentStatus(update.getAdPaymentStatus());
        }
        adPaymentJPARepository.save(adPayment);
    }

    @Override
    public void deleteById(int adPaymentId) {
        adPaymentJPARepository.deleteById(adPaymentId);
    }

    private static AdPaymentDTO.Find converToAdPaymentDTOFind(AdPayment adPayment){
        return AdPaymentDTO.Find.builder()
                .adPaymentId(adPayment.getAdPaymentId())
                .userId(adPayment.getUserId())
                .projectId(adPayment.getProjectId())
                .projectType(adPayment.getProjectId())
                .purchasedAt(adPayment.getPurchasedAt())
                .expiredAt(adPayment.getExpiredAt())
                .adPaymentStatus(adPayment.getAdPaymentStatus())
                .build();
    }
}
