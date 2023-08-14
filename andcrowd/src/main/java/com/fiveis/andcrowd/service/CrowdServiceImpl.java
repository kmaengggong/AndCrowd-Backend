package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.CrowdDTO;
import com.fiveis.andcrowd.entity.Crowd;
import com.fiveis.andcrowd.repository.CrowdJPARepository;
import com.fiveis.andcrowd.repository.DynamicCrowdBoardRepository;
import com.fiveis.andcrowd.repository.DynamicCrowdQnaReplyRepository;
import com.fiveis.andcrowd.repository.DynamicCrowdQnaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CrowdServiceImpl implements CrowdService {

    CrowdJPARepository crowdJPARepository;
    DynamicCrowdBoardRepository dynamicCrowdBoardRepository;
    DynamicCrowdQnaRepository dynamicCrowdQnaRepository;
    DynamicCrowdQnaReplyRepository dynamicCrowdQnaReplyRepository;

    @Autowired
    public CrowdServiceImpl(CrowdJPARepository crowdJPARepository,
                            DynamicCrowdBoardRepository dynamicCrowdBoardRepository,
                            DynamicCrowdQnaRepository dynamicCrowdQnaRepository,
                            DynamicCrowdQnaReplyRepository dynamicCrowdQnaReplyRepository){
        this.crowdJPARepository = crowdJPARepository;
        this.dynamicCrowdBoardRepository = dynamicCrowdBoardRepository;
        this.dynamicCrowdQnaRepository = dynamicCrowdQnaRepository;
        this.dynamicCrowdQnaReplyRepository = dynamicCrowdQnaReplyRepository;
    }

    @Override
    public Optional<CrowdDTO.FindById> findByCrowdId(int crowdId) {
        Optional<Crowd> crowdOptional = crowdJPARepository.findById(crowdId);
        return crowdOptional.map(this::convertToAndFindByCrowdId);
    }

    @Override
    public List<CrowdDTO.FindAllByUserId> findAllByUserIdList(int userId) {
        return crowdJPARepository.findAllByUserId(userId);
    }

    @Override
    public List<CrowdDTO.FindById> findAll() {
        List<Crowd> crowdList = crowdJPARepository.findAll();
        List<CrowdDTO.FindById> findByIdList = new ArrayList<>();

        for(Crowd crowd : crowdList){
            CrowdDTO.FindById result = convertToAndFindByCrowdId(crowd);
            findByIdList.add(result);
        }
        return findByIdList;
    }

    @Override
    public void deleteByCrowdId(int crowdId) {
        crowdJPARepository.deleteById(crowdId);
    }

    @Override
    public void save(Crowd crowd) {
        crowdJPARepository.save(crowd);
    }

    @Override
    public CrowdDTO.FindById convertToAndFindByCrowdId(Crowd crowd) {
        return CrowdDTO.FindById.builder()
                .crowdId(crowd.getCrowdId())
                .adId(crowd.getAdId())
                .andId(crowd.getAndId())
                .crowdCategoryId(crowd.getCrowdCategoryId())
                .crowdContent(crowd.getCrowdContent())
                .crowdEndDate(crowd.getCrowdEndDate())
                .crowdGoal(crowd.getCrowdGoal())
                .crowdImg1(crowd.getCrowdImg1())
                .crowdImg2(crowd.getCrowdImg2())
                .crowdImg3(crowd.getCrowdImg3())
                .crowdImg4(crowd.getCrowdImg4())
                .crowdImg5(crowd.getCrowdImg5())
                .crowdStatus(crowd.getCrowdStatus())
                .crowdTitle(crowd.getCrowdTitle())
                .headerImg(crowd.getHeaderImg())
                .isDeleted(crowd.isDeleted())
                .likeSum(crowd.getLikeSum())
                .publishedAt(crowd.getPublishedAt())
                .updatedAt(crowd.getUpdatedAt())
                .userId(crowd.getUserId())
                .viewCount(crowd.getViewCount())
                .build();
    }

    @Override
    public void update(Crowd crowd) {
        Optional<Crowd> crowdOptional = crowdJPARepository.findById(crowd.getCrowdId());
        if (crowdOptional.isPresent()) {
            Crowd updatedCrowd = crowdOptional.get();
            updatedCrowd.setCrowdTitle(crowd.getCrowdTitle());
            updatedCrowd.setCrowdContent(crowd.getCrowdContent());
            // 업데이트된 엔터티 저장
            crowdJPARepository.save(updatedCrowd);
        } else {
            // 해당 ID의 엔터티가 존재하지 않을 경우 예외 처리 또는 메시지 전달
            // 예: throw new EntityNotFoundException("Entity with ID " + crowd.getCrowdId() + " not found");
            throw new EntityNotFoundException("Entity with ID " + crowd.getCrowdId() + " not found");
        }
    }
}