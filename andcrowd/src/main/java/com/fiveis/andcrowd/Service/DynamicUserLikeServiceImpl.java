package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserLikeDTO;
import com.fiveis.andcrowd.dto.ProjectDTO;
import com.fiveis.andcrowd.repository.AndJPARepository;
import com.fiveis.andcrowd.repository.CrowdJPARepository;
import com.fiveis.andcrowd.repository.DynamicUserLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DynamicUserLikeServiceImpl implements DynamicUserLikeService{
    private static DynamicUserLikeRepository dynamicUserLikeRepository;
    private static AndJPARepository andJPARepository;
    private static CrowdJPARepository crowdJPARepository;

    @Autowired
    public DynamicUserLikeServiceImpl(DynamicUserLikeRepository dynamicUserLikeRepository,
                                      AndJPARepository andJPARepository,
                                      CrowdJPARepository crowdJPARepository){
        this.dynamicUserLikeRepository = dynamicUserLikeRepository;
        this.andJPARepository = andJPARepository;
        this.crowdJPARepository = crowdJPARepository;
    }

    public List<ProjectDTO.Find> findAll(String tableName){
        List<DynamicUserLikeDTO.Find> findList = dynamicUserLikeRepository.findAll(tableName);
        List<ProjectDTO.Find> projectList = new ArrayList<>();
//        for(DynamicUserLikeDTO.Find find : findList){
//            // 모임
//            if(find.getProjectType() == 0){
//                AndDTO.FindById andFind = andJPARepository.findById(find.getProjectId()).get();
//                ProjectDTO.Find projectFind = ProjectDTO.Find.builder()
//                        .projectId(andFind.getAndId())
//                        .projectType(1)
//                        .projectHeaderImg(andFind.getHeaderImg())
//                        .projectTitle(andFind.getCrowdTitle())
//                        .build();
//            }
//            // 펀딩
//            else{
//                CrowdDTO.FindById crowdFind = crowdJPARepository.findById(find.getProjectId()).get();
//                ProjectDTO.Find projectFind = ProjectDTO.Find.builder()
//                        .projectId(crowdFind.getCrowdId())
//                        .projectType(1)
//                        .projectHeaderImg(crowdFind.getHeaderImg())
//                        .projectTitle(crowdFind.getCrowdTitle())
//                        .build();
//            }
//        }
//        return projectList;
        return null;
    }

    public DynamicUserLikeDTO.Find findById(Map<String, ?> map){
        return dynamicUserLikeRepository.findById(map);
    }

    public void save(Map<String, ?> map){
        dynamicUserLikeRepository.save(map);
    }

    public void deleteById(Map<String, ?> map){
        dynamicUserLikeRepository.deleteById(map);
    }
}
