package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserLikeDTO;
import com.fiveis.andcrowd.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DynamicUserLikeService {
    List<ProjectDTO.Find> findAll(String tableName);
    DynamicUserLikeDTO.Find findById(Map<String, ?> map);
    void save(Map<String, ?> map);  // String tableName, DynamicUserLike dynamicUserLike
    void deleteById(Map<String, ?> map);  // String tableName, int uLikeId
}
