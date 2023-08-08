package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserAndDTO;
import com.fiveis.andcrowd.repository.DynamicUserAndRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DynamicUserAndServiceImpl implements DynamicUserAndService{
    private static DynamicUserAndRepository dynamicUserAndRepository;

    @Autowired
    public DynamicUserAndServiceImpl(DynamicUserAndRepository dynamicUserAndRepository){
        this.dynamicUserAndRepository = dynamicUserAndRepository;
    }

    public List<?> findAll(String tableName){
        return null;
    }
    public DynamicUserAndDTO.Find findById(Map<String, ?> map){
        return dynamicUserAndRepository.findById(map);
    }

    public void save(Map<String, ?> map){
        dynamicUserAndRepository.save(map);
    }

    public void deleteById(Map<String, ?> map){
        dynamicUserAndRepository.deleteById(map);
    }
}
