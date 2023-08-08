package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicAndQnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DynamicAndQnaRepository {
    DynamicAndQnaDTO.FindById findByAndQnaId(@Param("and_id") int andId, @Param("andQnaId") int andQnaId);
    void save(DynamicAndQnaDTO.Update andQnaInsertDTO);
    void update(DynamicAndQnaDTO.Update andQnaUpdateDTO);
    void deleteByAndQnaId(@Param("and_id") int andId, @Param("andQnaId") int andQnaId);

    void createAndQnaTable ();
    void dropAndQnaTable();
    void insertTestData();
}
