package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserOrderDTO;
import com.fiveis.andcrowd.entity.DynamicUserOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicUserOrderRepository {
    void createDynamicUserOrderTable(String userEmail);
    List<DynamicUserOrderDTO.Find> findAll(String userEmail);
    DynamicUserOrderDTO.Find findById(@Param("userEmail") String userEmail, @Param("uOrderId") int uOrderId);
    void save(@Param("userEmail") String userEmail, @Param("dynamicUserOrder") DynamicUserOrder dynamicUserOrder);
    void deleteById(@Param("userEmail") String userEmail, @Param("uOrderId") int uOrderId);
}
