package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.CrowdDTO;
import com.fiveis.andcrowd.entity.Crowd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrowdJPARepository extends JpaRepository<Crowd, Integer> {

    // userId를 기준으로 전체 글을 얻어오는 메서드를 쿼리메서드 방식으로 생성
    List<CrowdDTO.FindAllByUserId> findAllByUserId(int userId);

}