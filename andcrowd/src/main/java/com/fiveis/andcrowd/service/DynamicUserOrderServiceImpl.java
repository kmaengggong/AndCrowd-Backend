package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserOrderDTO;
import com.fiveis.andcrowd.entity.CrowdOrderDetails;
import com.fiveis.andcrowd.entity.DynamicUserOrder;
import com.fiveis.andcrowd.repository.DynamicUserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicUserOrderServiceImpl implements DynamicUserOrderService{
    private static DynamicUserOrderRepository dynamicUserOrderRepository;
//    private static CrowdOrderDetailsReposiory crowdORderDetailsReposiory;

    @Autowired
    public DynamicUserOrderServiceImpl(DynamicUserOrderRepository dynamicUserOrderRepository){//,
//                                       CrowdOrderDetailsReposiory crowdORderDetailsReposiory){
        this.dynamicUserOrderRepository = dynamicUserOrderRepository;
        //this.crowdOrderDetailsReposiory = crowdORderDetailsReposiory;
    }

    public List<CrowdOrderDetails> findAll(String userEmail){
        List<DynamicUserOrderDTO.Find> findList = dynamicUserOrderRepository.findAll(userEmail);
//        List<CrowdOrderDetails> orderList = new ArrayList<>();
//        for(DynamicUserOrderDTO.Find find : findList){
//            orderList.add(crowdOrderDetailsJPARepository.findById(find.getOrderId()).get());
//        }
//        return orderList;
        return null;
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
