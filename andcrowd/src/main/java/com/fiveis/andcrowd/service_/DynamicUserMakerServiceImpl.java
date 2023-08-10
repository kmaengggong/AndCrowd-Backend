package com.fiveis.andcrowd.service_;

import com.fiveis.andcrowd.dto.DynamicUserMakerDTO;
import com.fiveis.andcrowd.dto.ProjectDTO;
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
//        for(DynamicUserMakerDTO.Find find : findList){
//                // 모임
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
