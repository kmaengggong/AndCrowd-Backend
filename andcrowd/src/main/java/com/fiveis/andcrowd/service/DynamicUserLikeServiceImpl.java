package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserLikeDTO;
import com.fiveis.andcrowd.dto.ProjectDTO;
import com.fiveis.andcrowd.entity.DynamicUserLike;
import com.fiveis.andcrowd.repository.AndJPARepository;
import com.fiveis.andcrowd.repository.CrowdJPARepository;
import com.fiveis.andcrowd.repository.DynamicUserLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<ProjectDTO.Find> findAll(String userEmail){
        List<DynamicUserLikeDTO.Find> findList = dynamicUserLikeRepository.findAll(userEmail);
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

    public DynamicUserLikeDTO.Find findById(String userEmail, int uLikeId){
        return dynamicUserLikeRepository.findById(userEmail, uLikeId);
    }

    public void save(String userEmail, DynamicUserLike dynamicUserLike){
        dynamicUserLikeRepository.save(userEmail, dynamicUserLike);
    }

    public void deleteById(String userEmail, int uLikeId){
        dynamicUserLikeRepository.deleteById(userEmail, uLikeId);
    }
}
