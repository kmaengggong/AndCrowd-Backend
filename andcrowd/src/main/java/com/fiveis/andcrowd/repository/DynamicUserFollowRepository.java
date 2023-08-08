package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserFollowDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicUserFollowRepository {
    void createDynamicUserFollowTable(String tableName);
    List<DynamicUserFollowDTO.Find> findAll(String tableName);
    DynamicUserFollowDTO.Find findById(Map<String, ?> map);  // String tableName, int uFollowId
    void save(Map<String, ?> map);  // String tableName, DynamicUserFollow dynamicUserFollow
    void deleteById(Map<String, ?> map);  // String tableName, int uFollowId
}
