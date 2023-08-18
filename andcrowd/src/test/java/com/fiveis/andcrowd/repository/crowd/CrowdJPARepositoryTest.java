package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.entity.crowd.Crowd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CrowdJPARepositoryTest {

    @Autowired
    private CrowdJPARepository crowdJPARepository;

    @Test
    @Transactional
    public void FindAllTest(){
        List<Crowd> crowdList = crowdJPARepository.findAll();

        assertEquals(3, crowdList.size());
    }

    @Test
    @Transactional
    public void saveTest(){
        //given
        int crowdId = 1;
        int adId = 10;
        int andId = 100;
        int crowdCategoryId = 10;
        String crowdContent = "1번본문";
        LocalDateTime crowdEndDate = LocalDateTime.of(2023, 10, 19, 0,0,0);
        int crowdGoal = 10000000;
        String crowdImg1 = "펀딩이미지1";
        String crowdImg2 = "펀딩이미지2";
        String crowdImg3 = "펀딩이미지3";
        String crowdImg4 = "펀딩이미지4";
        String crowdImg5 = "펀딩이미지5";
        int crowdStatus = 1;
        String crowdTitle = "1번제목";
        String headerImg = "헤더이미지1";
        int userId = 1000;
        LocalDateTime publishedAt = LocalDateTime.now();

        //when
        Crowd newCrowd = Crowd.builder()
                .crowdId(crowdId)
                .adId(adId)
                .andId(andId)
                .crowdCategoryId(crowdCategoryId)
                .crowdContent(crowdContent)
                .crowdEndDate(crowdEndDate)
                .crowdGoal(crowdGoal)
                .crowdImg1(crowdImg1)
                .crowdImg2(crowdImg2)
                .crowdImg3(crowdImg3)
                .crowdImg4(crowdImg4)
                .crowdImg5(crowdImg5)
                .crowdStatus(crowdStatus)
                .crowdTitle(crowdTitle)
                .headerImg(headerImg)
                .userId(userId)
                .publishedAt(publishedAt)
                .build();

        crowdJPARepository.save(newCrowd);
        System.out.println(newCrowd);
        //then
    }

    @Test
    @Transactional
    @DisplayName("crowd글 삭제시 softdelte기능 정상 작동 확인")
    public void softDeleteTest(){
        // given
        // when
        // then
    }

}
