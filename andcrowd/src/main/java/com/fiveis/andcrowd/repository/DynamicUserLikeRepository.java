package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserLikeDTO;
import com.fiveis.andcrowd.entity.DynamicUserLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicUserLikeRepository {
    void createDynamicUserLikeTable(String userEmail);
    List<DynamicUserLikeDTO.Find> findAll(String userEmail);
    DynamicUserLikeDTO.Find findById(@Param("userEmail") String userEmail, @Param("uLikeId") int uLikeId);
    void save(@Param("userEmail") String userEmail, @Param("dynamicUserLike") DynamicUserLike dynamicUserLike);
    void deleteById(@Param("userEmail") String userEmail, @Param("uLikeId") int uLikeId);
}
