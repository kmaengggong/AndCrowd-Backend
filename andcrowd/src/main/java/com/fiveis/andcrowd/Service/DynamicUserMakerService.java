package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserMakerDTO;
import com.fiveis.andcrowd.dto.ProjectDTO;
import com.fiveis.andcrowd.entity.DynamicUserMaker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicUserMakerService {
    List<ProjectDTO.Find> findAll(String userEmail);  // 글의 이미지, 제목, 내용을 ProjectDTO를 통해 가져옴
    DynamicUserMakerDTO.Find findById(String userEmail, int uMakerId);
    void save(String userEmail, DynamicUserMaker dynamicUserMaker);
    void deleteById(String userEmail, int uMakerId);
}
