package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserLikeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicUserLikeRepository {
    void createDynamicUserLikeTable(String tableName);
    List<DynamicUserLikeDTO.Find> findAll(String tableName);
    DynamicUserLikeDTO.Find findById(Map<String, ?> map);
    void save(Map<String, ?> map);  // String tableName, DynamicUserLike dynamicUserLike
    void deleteById(Map<String, ?> map);  // String tableName, int uLikeId
}
