package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserAndDTO;
import com.fiveis.andcrowd.entity.And;
import com.fiveis.andcrowd.entity.DynamicUserAnd;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicUserAndService {
    List<And> findAll(String userEmail);  // 모임의 헤더 이미지, 제목, 내용 가져옴
    DynamicUserAndDTO.Find findById(String userEmail, int uAndId);
    void save(String userEmail, DynamicUserAnd dynamicUserAnd);
    void deleteById(String userEmail, int uAndId);
}
