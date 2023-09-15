package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdDTO;
import com.fiveis.andcrowd.entity.crowd.Crowd;
import com.fiveis.andcrowd.repository.crowd.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fiveis.andcrowd.dto.crowd.CrowdDTO.convertToCrowdFindDTO;

@Service
public class CrowdServiceImpl implements CrowdService {

    CrowdJPARepository crowdJPARepository;
    CrowdCategoryJPARepository crowdCategoryJPARepository;
    DynamicCrowdBoardRepository dynamicCrowdBoardRepository;
    DynamicCrowdQnaRepository dynamicCrowdQnaRepository;
    DynamicCrowdQnaReplyRepository dynamicCrowdQnaReplyRepository;
    DynamicCrowdRewardRepository dynamicCrowdRewardRepository;

    @Autowired
    public CrowdServiceImpl(CrowdJPARepository crowdJPARepository,
                            CrowdCategoryJPARepository crowdCategoryJPARepository,
                            DynamicCrowdBoardRepository dynamicCrowdBoardRepository,
                            DynamicCrowdQnaRepository dynamicCrowdQnaRepository,
                            DynamicCrowdQnaReplyRepository dynamicCrowdQnaReplyRepository,
                            DynamicCrowdRewardRepository dynamicCrowdRewardRepository){
        this.crowdJPARepository = crowdJPARepository;
        this.crowdCategoryJPARepository = crowdCategoryJPARepository;
        this.dynamicCrowdBoardRepository = dynamicCrowdBoardRepository;
        this.dynamicCrowdQnaRepository = dynamicCrowdQnaRepository;
        this.dynamicCrowdQnaReplyRepository = dynamicCrowdQnaReplyRepository;
        this.dynamicCrowdRewardRepository = dynamicCrowdRewardRepository;
    }

    @Override
    public Optional<CrowdDTO.FindById> findByCrowdId(int crowdId) {
        Optional<Crowd> crowdOptional = crowdJPARepository.findById(crowdId);
        return crowdOptional.map(CrowdDTO::convertToCrowdFindDTO);
    }

    @Override
    public List<CrowdDTO.FindAllByUserId> findAllByUserIdList(int userId) {
        return crowdJPARepository.findAllByUserId(userId);
    }

    @Override
    public List<CrowdDTO.FindById> findAllByIsDeletedFalse() {
        List<Crowd> crowdList = crowdJPARepository.findAllByIsDeletedFalse();
        List<CrowdDTO.FindById> findAllNotDeletedList = new ArrayList<>();

        for(Crowd crowd : crowdList){
            CrowdDTO.FindById result = convertToCrowdFindDTO(crowd);
            findAllNotDeletedList.add(result);
        }
        return findAllNotDeletedList;
    }

    @Override
    public List<CrowdDTO.FindById> findAll() {
        List<Crowd> crowdList = crowdJPARepository.findAll();
        List<CrowdDTO.FindById> findByIdList = new ArrayList<>();

        for(Crowd crowd : crowdList){
            CrowdDTO.FindById result = convertToCrowdFindDTO(crowd);
            findByIdList.add(result);
        }
        return findByIdList;
    }

    @Transactional // 둘다 실행되던지 둘 다 실행 안되던지(갑작스런 서버 다운이 되었을 경우를 대비해)
    @Override
    public void deleteByCrowdId(int crowdId) {
        dynamicCrowdQnaReplyRepository.deleteAll(crowdId);
        dynamicCrowdQnaRepository.deleteAll(crowdId);
        dynamicCrowdBoardRepository.deleteByCrowdId(crowdId);
        dynamicCrowdRewardRepository.deleteAll(crowdId);
        crowdCategoryJPARepository.deleteById(crowdId);
        crowdJPARepository.deleteById(crowdId);
        Optional<Crowd> crowdOptional = crowdJPARepository.findById(crowdId);

        if(crowdOptional.isPresent()) {
            Crowd crowd = crowdOptional.get();
            crowd.setDeleted(true);
            crowdJPARepository.save(crowd);
        }
    }

    @Override
    public void save(Crowd crowd) {
        Crowd insertCrowd = crowdJPARepository.save(crowd);
        dynamicCrowdRewardRepository.createDynamicCrowdRewardTable(insertCrowd.getCrowdId());
        dynamicCrowdBoardRepository.createDynamicCrowdBoardTable(insertCrowd.getCrowdId());
        dynamicCrowdQnaRepository.createDynamicCrowdQnaTable(insertCrowd.getCrowdId());
        dynamicCrowdQnaReplyRepository.createDynamicCrowdQnaReplyTable(insertCrowd.getCrowdId());
    }

//    @Override
//    public CrowdDTO.FindById convertToAndFindByCrowdId(Crowd crowd) {
//        return CrowdDTO.FindById.builder()
//                .crowdId(crowd.getCrowdId())
//                .adId(crowd.getAdId())
//                .andId(crowd.getAndId())
//                .crowdCategoryId(crowd.getCrowdCategoryId())
//                .crowdContent(crowd.getCrowdContent())
//                .crowdEndDate(crowd.getCrowdEndDate())
//                .crowdGoal(crowd.getCrowdGoal())
//                .crowdImg1(crowd.getCrowdImg1())
//                .crowdImg2(crowd.getCrowdImg2())
//                .crowdImg3(crowd.getCrowdImg3())
//                .crowdImg4(crowd.getCrowdImg4())
//                .crowdImg5(crowd.getCrowdImg5())
//                .crowdStatus(crowd.getCrowdStatus())
//                .crowdTitle(crowd.getCrowdTitle())
//                .headerImg(crowd.getHeaderImg())
//                .isDeleted(crowd.isDeleted())
//                .likeSum(crowd.getLikeSum())
//                .publishedAt(crowd.getPublishedAt())
//                .updatedAt(crowd.getUpdatedAt())
//                .userId(crowd.getUserId())
//                .viewCount(crowd.getViewCount())
//                .build();
//    }

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

    // 보정된 pageNum으로 가공해주는 메서드 (pageNum을 정확한 숫자로 교정해주는 역할)
    public int getCalibratedPageNum(Long pageNum){
        // 사용자가 페이지입력시 음수를 넘겼거나 아무것도 안 넣은 경우
        if(pageNum == null || pageNum <= 0L){
            pageNum = 1L; // 첫번째 페이지로 가도록 하는 구문
        }else {
            // 총 페이지 개수를 구하는 로직
            int totalPagesCount = (int) Math.ceil(crowdJPARepository.count() / 10.0);

            pageNum = pageNum > totalPagesCount ? totalPagesCount : pageNum;
        }
        return pageNum.intValue();
    }

    @Override
    public void updateStatus(int crowdId, int crowdStatus) {
        Optional<Crowd> optionalCrowd = crowdJPARepository.findById(crowdId);
        if (optionalCrowd.isPresent()) {
            Crowd updatedCrowd = optionalCrowd.get();
            updatedCrowd.setCrowdStatus(crowdStatus);

            // 변경된 And 객체를 다시 저장
            crowdJPARepository.save(updatedCrowd);
        }
    }
}