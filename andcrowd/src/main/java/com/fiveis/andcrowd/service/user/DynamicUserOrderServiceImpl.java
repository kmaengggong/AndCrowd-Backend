package com.fiveis.andcrowd.service.user;

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

    public List<CrowdOrderDetails> findAll(String userEmail){
        List<DynamicUserOrderDTO.Find> findList = dynamicUserOrderRepository.findAll(userEmail);
        List<CrowdOrderDetails> orderList = new ArrayList<>();
        for(DynamicUserOrderDTO.Find find : findList){
            orderList.add(crowdOrderDetailsJPARepository.findById(find.getOrderId()).get());
        }
        return orderList;
    }

    public DynamicUserOrderDTO.Find findById(String userEmail, int uOrderId){
        return dynamicUserOrderRepository.findById(userEmail, uOrderId);
    }

    public void save(String userEmail, DynamicUserOrder dynamicUserOrder){
        dynamicUserOrderRepository.save(userEmail, dynamicUserOrder);
    }
    public void deleteById(String userEmail, int uOrderId){
        dynamicUserOrderRepository.deleteById(userEmail, uOrderId);
    }
}
