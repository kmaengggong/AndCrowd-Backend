package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdDTO;
import com.fiveis.andcrowd.entity.crowd.Crowd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CrowdService {

    Optional<CrowdDTO.FindById> findByCrowdId(int crowdId);

    List<CrowdDTO.FindAllByUserId> findAllByUserIdList(int userId); // userId가 이용한 crowd글 조회

    List<CrowdDTO.FindById> findAllByIsDeletedFalse(); // crowd글 전체조회

    List<CrowdDTO.FindById> findAll(); // crowd글 선택시 전체조회

    void deleteByCrowdId(int crowdId); // 삭제

    void save(Crowd crowd); // 생성

//    public CrowdDTO.FindById convertToAndFindByCrowdId(Crowd crowd);

    void update(Crowd crowd); // 수정
}