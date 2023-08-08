package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicAndQnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicAndQnaRepository {
    List<DynamicAndQnaDTO.FindById> findAll(int andId);
    DynamicAndQnaDTO.FindById findByAndQnaId(@Param("and_id") int andId, @Param("andQnaId") int andQnaId);
    void save(DynamicAndQnaDTO.Update andQnaInsertDTO);
    void update(DynamicAndQnaDTO.Update andQnaUpdateDTO);
    void deleteByAndQnaId(@Param("andId") int andId, @Param("andQnaId") int andQnaId);

    void createAndQnaTable ();
    void dropAndQnaTable();
    void insertTestData();
}
