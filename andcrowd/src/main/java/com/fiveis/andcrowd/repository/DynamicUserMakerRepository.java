package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserMakerDTO;
import com.fiveis.andcrowd.entity.DynamicUserMaker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicUserMakerRepository {
    void createDynamicUserMakerTable(String userEmail);
    List<DynamicUserMakerDTO.Find> findAll(String userEmail);
    DynamicUserMakerDTO.Find findById(@Param("userEmail") String userEmail, @Param("uMakerId") int uMakerId);
    void save(@Param("userEmail") String userEmail, DynamicUserMaker dynamicUserMaker);
    void deleteById(@Param("userEmail") String userEmail, @Param("uMakerId") int uMakerId);
}
