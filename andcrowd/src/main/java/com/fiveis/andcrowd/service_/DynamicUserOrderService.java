package com.fiveis.andcrowd.service_;

import com.fiveis.andcrowd.dto.DynamicUserOrderDTO;
import com.fiveis.andcrowd.entity.CrowdOrderDetails;
import com.fiveis.andcrowd.entity.DynamicUserOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicUserOrderService {
    List<CrowdOrderDetails> findAll(String userEmail);  // CrowdOrderDetails 가져와야 함
    DynamicUserOrderDTO.Find findById(String userEmail, int uOrderId);
    void save(String userEmail, DynamicUserOrder dynamicUserOrder);
    void deleteById(String userEmail, int uOrderId);
}
