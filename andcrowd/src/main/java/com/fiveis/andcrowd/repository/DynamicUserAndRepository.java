package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserAndDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicUserAndRepository {
    void createDynamicUserAndTable(String tableName);
    List<DynamicUserAndDTO.Find> findAll(String tableName);
    DynamicUserAndDTO.Find findById(Map<String, ?> map);  // String tableName, int uAndId
    void save(Map<String, ?> map);  // String tableName, DynamicUserAnd dynamicUserAnd
    void deleteById(Map<String, ?> map);  // String tableName, int uAndId
}
