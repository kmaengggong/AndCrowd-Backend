package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserOrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicUserOrderRepository {
    void createDynamicUserOrderTable(String tableName);
    List<DynamicUserOrderDTO.Find> findAll(String tableName);
    DynamicUserOrderDTO.Find findById(Map<String, ?> map);
    void save(Map<String, ?> map);  // String tableName, DynamicUserOrder dynamicUserOrder
    void deleteById(Map<String, ?> map);  // String tableName, int uOrderId
}
