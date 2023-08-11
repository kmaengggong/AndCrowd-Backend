package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.AdPaymentDTO;
import com.fiveis.andcrowd.entity.AdPayment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdPaymentService {
    List<AdPaymentDTO.Find> findAll();
    AdPaymentDTO.Find findById(int adPaymentId);
    void save(AdPayment adPayment);
    void update(AdPaymentDTO.Update update);
    void deleteById(int adPaymentId);
}
