package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicAndBoardDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicAndBoardRepository {

    List<DynamicAndBoardDTO.FindById> findAll(int andId);
    DynamicAndBoardDTO.FindById findByAndBoardId(@Param("and_id") int andId, @Param("andBoardId") int andBoardId);
    void save(DynamicAndBoardDTO.Update andBoardInsertDTO);
    void update(DynamicAndBoardDTO.Update andBoardUpdateDTO);
    void deleteByAndBoardId(@Param("andId") int andId, @Param("andBoardId") int andBoardId);

    void createAndBoardTable ();
    void dropAndBoardTable();

    void insertTestData();
}
