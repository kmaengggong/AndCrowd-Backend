package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicAndQnaDTO;
import com.fiveis.andcrowd.repository.DynamicAndQnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicAndQnaServiceImpl implements DynamicAndQnaService{

    DynamicAndQnaRepository dynamicAndQnaRepository;

    @Autowired
    public DynamicAndQnaServiceImpl(DynamicAndQnaRepository dynamicAndQnaRepository){
        this.dynamicAndQnaRepository = dynamicAndQnaRepository;
    }

    @Override
    public List<DynamicAndQnaDTO.FindById> findAll(int andId) {
        return dynamicAndQnaRepository.findAll(andId);
    }

    @Override
    public List<DynamicAndQnaDTO.FindById> findAllNotDeleted(int andId) {
        return dynamicAndQnaRepository.findAllNotDeleted(andId);
    }

    @Override
    public DynamicAndQnaDTO.FindById findByAndQnaId(int andId, int andQnaId) {
        return dynamicAndQnaRepository.findByAndQnaId(andId, andQnaId);
    }

    @Override
    public void save(DynamicAndQnaDTO.Update andQnaInsertDTO) {
        dynamicAndQnaRepository.save(andQnaInsertDTO);
    }

    @Override
    public void update(DynamicAndQnaDTO.Update andQnaUpdateDTO) {
        dynamicAndQnaRepository.update(andQnaUpdateDTO);
    }

    @Override
    public void deleteByAndQnaId(int andId, int andQnaId) {
        dynamicAndQnaRepository.deleteByAndQnaId(andId, andQnaId);
    }


}
