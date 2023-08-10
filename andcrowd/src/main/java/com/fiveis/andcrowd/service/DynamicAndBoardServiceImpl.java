package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicAndBoardDTO;
import com.fiveis.andcrowd.repository.DynamicAndBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicAndBoardServiceImpl implements DynamicAndBoardService {
    DynamicAndBoardRepository dynamicAndBoardRepository;

    @Autowired
    public DynamicAndBoardServiceImpl(DynamicAndBoardRepository dynamicAndBoardRepository) {
        this.dynamicAndBoardRepository = dynamicAndBoardRepository;
    }

    @Override
    public List<DynamicAndBoardDTO.FindById> findAll(int andId) {
        return dynamicAndBoardRepository.findAll(andId);
    }

    @Override
    public DynamicAndBoardDTO.FindById findByAndBoardId(int andId, int andBoardId) {
        return dynamicAndBoardRepository.findByAndBoardId(andId, andBoardId);
    }

    @Override
    public void save(DynamicAndBoardDTO.Update andBoardInsertDTO) {
        dynamicAndBoardRepository.save(andBoardInsertDTO);
    }

    @Override
    public void update(DynamicAndBoardDTO.Update andBoardUpdateDTO) {
        dynamicAndBoardRepository.update(andBoardUpdateDTO);
    }

    @Override
    public void deleteByAndBoardId(int andId, int andBoardId) {
        dynamicAndBoardRepository.deleteByAndBoardId(andId, andBoardId);
    }
    @Override
    public void deleteByAndId(int andId) {
        dynamicAndBoardRepository.deleteByAndId(andId);
    }

    @Override
    public void createAndBoardTable() {
        dynamicAndBoardRepository.createAndBoardTable();
    }

    @Override
    public void dropAndBoardTable() {
        dynamicAndBoardRepository.dropAndBoardTable();
    }

    @Override
    public void insertTestData() {
        dynamicAndBoardRepository.insertTestData();
    }
}
