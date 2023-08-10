package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicAndBoardDTO;
<<<<<<< Updated upstream
=======
import org.apache.ibatis.annotations.Mapper;
>>>>>>> Stashed changes
import org.apache.ibatis.annotations.Param;

import java.util.List;

<<<<<<< Updated upstream
public interface DynamicAndBoardRepository {

    List<DynamicAndBoardDTO.FindById> findAll(int andId);
    DynamicAndBoardDTO.FindById findByAndBoardId(@Param("and_id") int andId, @Param("andBoardId") int andBoardId);
    void save(DynamicAndBoardDTO.Update andBoardInsertDTO);
    void update(DynamicAndBoardDTO.Update andBoardUpdateDTO);
    void deleteByAndBoardId(@Param("andId") int andId, @Param("andBoardId") int andBoardId);

    void createAndBoardTable ();
    void dropAndBoardTable();

    void insertTestData();
=======
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

>>>>>>> Stashed changes
}
