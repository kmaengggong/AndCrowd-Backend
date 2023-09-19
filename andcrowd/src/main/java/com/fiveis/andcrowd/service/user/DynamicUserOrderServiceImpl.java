package com.fiveis.andcrowd.service.user;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.dto.user.DynamicUserOrderDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import com.fiveis.andcrowd.entity.user.DynamicUserOrder;
import com.fiveis.andcrowd.repository.crowd.CrowdOrderDetailsJPARepository;
import com.fiveis.andcrowd.repository.user.DynamicUserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicUserOrderServiceImpl implements DynamicUserOrderService{
    private static DynamicUserOrderRepository dynamicUserOrderRepository;
    private static CrowdOrderDetailsJPARepository crowdOrderDetailsJPARepository;

    @Autowired
    public DynamicUserOrderServiceImpl(DynamicUserOrderRepository dynamicUserOrderRepository,
                                       CrowdOrderDetailsJPARepository crowdOrderDetailsJPARepository){
        this.dynamicUserOrderRepository = dynamicUserOrderRepository;
        this.crowdOrderDetailsJPARepository = crowdOrderDetailsJPARepository;
    }

    public List<CrowdOrderDetailsDTO.FindById> findAll(String userEmail){
        List<DynamicUserOrderDTO.Find> findList = dynamicUserOrderRepository.findAll(userEmail);
        List<CrowdOrderDetailsDTO.FindById> orderList = new ArrayList<>();
        for(DynamicUserOrderDTO.Find find : findList){
            if(crowdOrderDetailsJPARepository.findById(find.getOrderId()).isEmpty()) continue;
            CrowdOrderDetails crowdOrderDetails = crowdOrderDetailsJPARepository.findById(find.getOrderId()).get();
            orderList.add(CrowdOrderDetailsDTO.FindById.convertToFindByIdDTO(crowdOrderDetails));
        }
        return orderList;
    }

    public DynamicUserOrderDTO.Find findById(String userEmail, int uOrderId){
        return dynamicUserOrderRepository.findById(userEmail, uOrderId);
    }

    public boolean save(String userEmail, DynamicUserOrder dynamicUserOrder){
        // 존재하지 않는 projectId
        if(crowdOrderDetailsJPARepository.findById(dynamicUserOrder.getOrderId()).isEmpty()) return false;
        // user_like에 이미 존재
        if(dynamicUserOrderRepository.findByOrderId(userEmail, dynamicUserOrder.getOrderId()) != null) return false;
        // 그 외에는 저장 성공
        dynamicUserOrderRepository.save(userEmail, dynamicUserOrder);
        return true;
    }
    public void deleteById(String userEmail, int uOrderId){
        dynamicUserOrderRepository.deleteById(userEmail, uOrderId);
    }

    public void deleteByOrderId(String userEmail, int orderId){
        dynamicUserOrderRepository.deleteByOrderId(userEmail, orderId);
    }

    public void deleteTableByUserEmail(String userEmail){
        dynamicUserOrderRepository.deleteTableByUserEmail(userEmail);
    }
}
