package com.fiveis.andcrowd.service.user;

import com.fiveis.andcrowd.dto.user.DynamicUserOrderDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import com.fiveis.andcrowd.entity.user.DynamicUserOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicUserOrderService {
    List<CrowdOrderDetails> findAll(String userEmail);  // CrowdOrderDetails 가져와야 함
    DynamicUserOrderDTO.Find findById(String userEmail, int uOrderId);
    void save(String userEmail, DynamicUserOrder dynamicUserOrder);
    void deleteById(String userEmail, int uOrderId);
}
