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
            //orderList.add(CrowdOrderDetailsDTO.convertToFindByIdDTO(crowdOrderDetails));
        }
        return orderList;
    }

    public DynamicUserOrderDTO.Find findById(String userEmail, int uOrderId){
        return dynamicUserOrderRepository.findById(userEmail, uOrderId);
    }

    public void save(String userEmail, DynamicUserOrder dynamicUserOrder){
        if(dynamicUserOrderRepository.findByOrderId(userEmail, dynamicUserOrder.getOrderId()) != null) return;
        dynamicUserOrderRepository.save(userEmail, dynamicUserOrder);
    }
    public void deleteById(String userEmail, int uOrderId){
        dynamicUserOrderRepository.deleteById(userEmail, uOrderId);
    }
}
