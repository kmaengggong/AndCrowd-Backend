package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicAndBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicAndBoardRepository {
    List<DynamicAndBoardDTO.FindById> findAll(int andId);
    DynamicAndBoardDTO.FindById findByAndBoardId(@Param("andId") int andId, @Param("andBoardId") int andBoardId);
    void save(DynamicAndBoardDTO.Update andBoardInsertDTO);
    void update(DynamicAndBoardDTO.Update andBoardUpdateDTO);
    void deleteByAndId(@Param("andId") int andId);
    void deleteByAndBoardId(@Param("andId") int andId, @Param("andBoardId") int andBoardId);

    void createAndBoardTable();
    void dropAndBoardTable();
    void insertTestData();

    void createAndTestBoardTable();
}
