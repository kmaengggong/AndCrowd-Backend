package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserAndDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DynamicUserAndService {
    List<?> findAll(String tableName);  // 헤더 이미지, 제목, 내용 가져와야 함
    DynamicUserAndDTO.Find findById(Map<String, ?> map);  // String tableName, int uAndId
    void save(Map<String, ?> map);  // String tableName, DynamicUserFollow dynamicUserFollow
    void deleteById(Map<String, ?> map);  // String tableName, int uFollowId
}
