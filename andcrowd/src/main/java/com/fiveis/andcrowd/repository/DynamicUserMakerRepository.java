package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserMakerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicUserMakerRepository {
    void createDynamicUserMakerTable(String tableName);
    List<DynamicUserMakerDTO.Find> findAll(String tableName);
    DynamicUserMakerDTO.Find findById(Map<String, ?> map);
    void save(Map<String, ?> map);  // String tableName, DynamicUserMaker dynamicUserMaker
    void deleteById(Map<String, ?> map);  // String tableName, int uMakerId
}
