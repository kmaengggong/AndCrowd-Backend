package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserAndDTO;
import com.fiveis.andcrowd.entity.DynamicUserAnd;
import com.fiveis.andcrowd.repository.DynamicUserAndRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicUserAndServiceImpl implements DynamicUserAndService{
    private static DynamicUserAndRepository dynamicUserAndRepository;

    @Autowired
    public DynamicUserAndServiceImpl(DynamicUserAndRepository dynamicUserAndRepository){
        this.dynamicUserAndRepository = dynamicUserAndRepository;
    }

    public List<?> findAll(String userEmail){
        return null;
    }
    public DynamicUserAndDTO.Find findById(String userEmail, int uAndId){
        return dynamicUserAndRepository.findById(userEmail, uAndId);
    }

    public void save(String userEmail, DynamicUserAnd dynamicUserAnd){
        dynamicUserAndRepository.save(userEmail, dynamicUserAnd);
    }

    public void deleteById(String userEmail, int uAndId){
        dynamicUserAndRepository.deleteById(userEmail, uAndId);
    }
}
