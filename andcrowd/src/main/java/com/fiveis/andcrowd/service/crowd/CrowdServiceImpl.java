package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdDTO;
import com.fiveis.andcrowd.entity.crowd.Crowd;
import com.fiveis.andcrowd.entity.user.DynamicUserMaker;
import com.fiveis.andcrowd.repository.crowd.*;
import com.fiveis.andcrowd.repository.user.DynamicUserMakerRepository;
import com.fiveis.andcrowd.repository.user.UserJPARepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.fiveis.andcrowd.dto.crowd.CrowdDTO.convertToCrowdFindDTO;
import static com.fiveis.andcrowd.entity.user.User.toTableName;

@Service
@RequiredArgsConstructor
public class CrowdServiceImpl implements CrowdService {

    private static final int PAGE_SIZE = 6;

    private final CrowdJPARepository crowdJPARepository;
    private final CrowdCategoryJPARepository crowdCategoryJPARepository;
    private final DynamicCrowdBoardRepository dynamicCrowdBoardRepository;
    private final DynamicCrowdQnaRepository dynamicCrowdQnaRepository;
    private final DynamicCrowdQnaReplyRepository dynamicCrowdQnaReplyRepository;
    private final DynamicCrowdRewardRepository dynamicCrowdRewardRepository;
    private final UserJPARepository userJPARepository;
    private final DynamicUserMakerRepository dynamicUserMakerRepository;


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

        String userEmail = toTableName(userJPARepository.findByUserId(insertCrowd.getUserId()).get().getUserEmail());

        DynamicUserMaker dynamicUserMaker = DynamicUserMaker.builder()
                .projectId(insertCrowd.getCrowdId())
                .projectType(1)
                .build();
        dynamicUserMakerRepository.save(userEmail, dynamicUserMaker);
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

            // 업데이트된 엔터티 저장
            crowdJPARepository.save(updatedCrowd
                    .updateCrowd(crowd.getCrowdTitle(),
                    crowd.getCrowdContent(),
                    crowd.getCrowdCategoryId(),
                    crowd.getCrowdStatus(),
                    crowd.getCrowdGoal(),
                    crowd.getCrowdEndDate()));
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

            // 변경된 And 객체를 다시 저장
            crowdJPARepository.save(updatedCrowd.updateCrowdStatus(crowdStatus));
        }
    }

    @Override
    public Page<CrowdDTO.FindById> searchPageList(Integer crowdStatus, String sortField, Integer pageNumber, Integer crowdCategoryId, String keyword, Pageable pageable) {
        if (Objects.equals(sortField, "crowdEndDate")) {
            System.out.println(" sort field : crowdEndDate !!!!!");
            pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(Sort.Direction.ASC, sortField));
        } else {
            pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(Sort.Direction.DESC, sortField));
        }

        Specification<Crowd> spec = CrowdSpecifications.findByCriteria(crowdCategoryId, crowdStatus, keyword);
        Page<Crowd> entityPage = crowdJPARepository.findAll(spec, pageable);

        Page<CrowdDTO.FindById> pageList = entityPage.map(crowd -> convertToCrowdFindDTO(crowd));

        return pageList;
    }

}