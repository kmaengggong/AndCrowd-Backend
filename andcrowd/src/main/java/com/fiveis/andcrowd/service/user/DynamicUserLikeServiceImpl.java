package com.fiveis.andcrowd.service.user;

import com.fiveis.andcrowd.dto.user.DynamicUserLikeDTO;
import com.fiveis.andcrowd.dto.etc.ProjectDTO;
import com.fiveis.andcrowd.entity.and.And;
import com.fiveis.andcrowd.entity.crowd.Crowd;
import com.fiveis.andcrowd.entity.user.DynamicUserLike;
import com.fiveis.andcrowd.repository.and.AndJPARepository;
import com.fiveis.andcrowd.repository.crowd.CrowdJPARepository;
import com.fiveis.andcrowd.repository.user.DynamicUserLikeRepository;
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
        for(DynamicUserLikeDTO.Find find : findList){
            // 모임
            if(find.getProjectType() == 0){
                if(andJPARepository.findById(find.getProjectId()).isEmpty()) continue;
                And and = andJPARepository.findById(find.getProjectId()).get();
                ProjectDTO.Find projectFind = ProjectDTO.Find.builder()
                        .projectId(and.getAndId())
                        .projectType(1)
                        .projectHeaderImg(and.getAndHeaderImg())
                        .projectTitle(and.getAndTitle())
                        .build();
                projectList.add(projectFind);
            }
            // 펀딩
            else{
                if(crowdJPARepository.findById(find.getProjectId()).isEmpty()) continue;
                Crowd crowd = crowdJPARepository.findById(find.getProjectId()).get();
                ProjectDTO.Find projectFind = ProjectDTO.Find.builder()
                        .projectId(crowd.getCrowdId())
                        .projectType(1)
                        .projectHeaderImg(crowd.getHeaderImg())
                        .projectTitle(crowd.getCrowdTitle())
                        .build();
                projectList.add(projectFind);
            }
        }
        return projectList;
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
