package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserOrderDTO;
import com.fiveis.andcrowd.entity.DynamicUserOrder;
import com.fiveis.andcrowd.repository.DynamicUserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicUserOrderServiceImpl implements DynamicUserOrderService{
    private static DynamicUserOrderRepository dynamicUserOrderRepository;

    @Autowired
    public DynamicUserOrderServiceImpl(DynamicUserOrderRepository dynamicUserOrderRepository){
        this.dynamicUserOrderRepository = dynamicUserOrderRepository;
    }

    public List<?> findAll(String userEmail){
        // CrowdOrderDetails
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
