package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicAndBoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicAndBoardService {
    List<DynamicAndBoardDTO.FindById> findAll(int andId);
    DynamicAndBoardDTO.FindById findByAndBoardId(int andId, int andBoardId);
    void deleteByAndId(int andId);
    void deleteByAndBoardId(int andId, int andBoardId);
    void save(DynamicAndBoardDTO.Update dynamicAndBoardUpdateDTO);
    void update(DynamicAndBoardDTO.Update dynamicAndBoardUpdateDTO);

    void createAndBoardTable();
    void dropAndBoardTable();
    void insertTestData();
}
