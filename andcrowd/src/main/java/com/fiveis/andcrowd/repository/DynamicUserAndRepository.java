package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserAndDTO;
import com.fiveis.andcrowd.entity.DynamicUserAnd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicUserAndRepository {
    void createDynamicUserAndTable(String userEmail);
    List<DynamicUserAndDTO.Find> findAll(String userEmail);
    DynamicUserAndDTO.Find findById(@Param("userEmail") String userEmail, @Param("uAndId") int uAndId);
    void save(@Param("userEmail") String userEmail, @Param("dynamicUserAnd") DynamicUserAnd dynamicUserAnd);
    void deleteById(@Param("userEmail") String userEmail, @Param("uAndId") int uAndId);
}
