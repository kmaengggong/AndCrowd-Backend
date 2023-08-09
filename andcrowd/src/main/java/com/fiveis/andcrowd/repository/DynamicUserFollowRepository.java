package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserFollowDTO;
import com.fiveis.andcrowd.entity.DynamicUserFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicUserFollowRepository {
    void createDynamicUserFollowTable(String userEmail);
    List<DynamicUserFollowDTO.Find> findAll(String userEmail);
    DynamicUserFollowDTO.Find findById(@Param("userEmail") String userEmail, @Param("uFollowId") int uFollowId);
    void save(@Param("userEmail") String userEmail, @Param("dynamicUserFollow") DynamicUserFollow dynamicUserFollow);
    void deleteById(@Param("userEmail") String userEmail, @Param("uFollowId") int uFollowId);
}
