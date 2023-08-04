package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.Crowd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrowdJPARepository extends JpaRepository<Crowd, Integer> {

    // userId를 기준으로 전체 글을 얻어오는 메서드를 쿼리메서드 방식으로 생성
    List<Crowd> findAllByUserId(int userId);

}