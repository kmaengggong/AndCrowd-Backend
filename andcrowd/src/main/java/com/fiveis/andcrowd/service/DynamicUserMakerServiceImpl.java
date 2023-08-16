package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserMakerDTO;
import com.fiveis.andcrowd.dto.ProjectDTO;
import com.fiveis.andcrowd.entity.And;
import com.fiveis.andcrowd.entity.Crowd;
import com.fiveis.andcrowd.entity.DynamicUserMaker;
import com.fiveis.andcrowd.repository.AndJPARepository;
import com.fiveis.andcrowd.repository.CrowdJPARepository;
import com.fiveis.andcrowd.repository.DynamicUserMakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicUserMakerServiceImpl implements DynamicUserMakerService{
    private static DynamicUserMakerRepository dynamicUserMakerRepository;
    private static AndJPARepository andJPARepository;
    private static CrowdJPARepository crowdJPARepository;

    @Autowired
    public DynamicUserMakerServiceImpl(DynamicUserMakerRepository dynamicUserMakerRepository,
                                       AndJPARepository andJPARepository,
                                       CrowdJPARepository crowdJPARepository){
            this.dynamicUserMakerRepository = dynamicUserMakerRepository;
            this.andJPARepository = andJPARepository;
            this.crowdJPARepository = crowdJPARepository;
    }

    public List<ProjectDTO.Find> findAll(String userEmail){
        List<DynamicUserMakerDTO.Find> findList = dynamicUserMakerRepository.findAll(userEmail);
        List<ProjectDTO.Find> projectList = new ArrayList<>();
        for(DynamicUserMakerDTO.Find find : findList){
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

    public DynamicUserMakerDTO.Find findById(String userEmail, int uMakerId){
        return dynamicUserMakerRepository.findById(userEmail, uMakerId);
    }

    public void save(String userEmail, DynamicUserMaker dynamicUserMaker){
        dynamicUserMakerRepository.save(userEmail, dynamicUserMaker);
    }

    public void deleteById(String userEmail, int uMakerId){
        dynamicUserMakerRepository.deleteById(userEmail, uMakerId);
    }
}
