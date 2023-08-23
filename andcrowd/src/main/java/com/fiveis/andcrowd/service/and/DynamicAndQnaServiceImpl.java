package com.fiveis.andcrowd.service.and;

import com.fiveis.andcrowd.dto.and.DynamicAndQnaDTO;
import com.fiveis.andcrowd.repository.and.DynamicAndQnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        andQnaInsertDTO.setPublishedAt(LocalDateTime.now());
        andQnaInsertDTO.setUpdatedAt(LocalDateTime.now());
        dynamicAndQnaRepository.save(andQnaInsertDTO);
    }

    @Override
    public void update(DynamicAndQnaDTO.Update andQnaUpdateDTO) {
        andQnaUpdateDTO.setUpdatedAt(LocalDateTime.now());
        dynamicAndQnaRepository.update(andQnaUpdateDTO);
    }

    @Override
    public void deleteByAndQnaId(int andId, int andQnaId) {
        dynamicAndQnaRepository.deleteByAndQnaId(andId, andQnaId);
    }


}
