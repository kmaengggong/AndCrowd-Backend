package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserFollowDTO;
import com.fiveis.andcrowd.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DynamicUserFollowService {
    List<User> findAll(String tableName);
    void save(Map<String, ?> map);  // String tableName, DynamicUserFollow dynamicUserFollow
    DynamicUserFollowDTO.Find findById(Map<String, ?> map);  // String tableName, int uFollowId
    void deleteById(Map<String, ?> map);  // String tableName, int uFollowId
}
