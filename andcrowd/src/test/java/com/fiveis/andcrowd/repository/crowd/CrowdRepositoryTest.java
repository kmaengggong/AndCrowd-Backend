package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.entity.crowd.Crowd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CrowdRepositoryTest {

    @Autowired
    private CrowdJPARepository crowdJPARepository;

    @Test
    @Transactional
    public void FindAllTest(){
        List<Crowd> crowdList = crowdJPARepository.findAll();

        assertEquals(3, crowdList.size());
    }

//    @Test
//    @Transactional
//    public void SoftDeleteById(){
//        //given
//        Crowd newCrowd = Crowd.builder()
//                .crowdId(1)
//                .adId(10)
//                .andId(100)
//                .crowdCategoryId(10)
//                .crowdContent("1번본문")
//                .crowdEndDate(LocalDateTime.now().plusDays(7))
//                .crowdGoal(1000000)
//                .crowdImg1("펀딩이미지1")
//                .crowdImg2("펀딩이미지2")
//                .crowdImg3("펀딩이미지3")
//                .crowdImg4("펀딩이미지4")
//                .crowdImg5("펀딩이미지5")
//                .crowdStatus(1)
//                .crowdTitle("1번제목")
//                .headerImg("헤더이미지1")
//                .userId(1000)
//                .publishedAt(LocalDateTime.now().plusDays(2))
//                .build();
//
//        Crowd savedCrowd = crowdJPARepository.save(newCrowd);
//        crowdJPARepository.flush();
//
//        int crowdIdDelete = savedCrowd.getCrowdId();
//
//        //when
//        crowdJPARepository.deleteById(crowdIdDelete);
//        crowdJPARepository.flush();
//
//        //then
//        Crowd deletedCrowd = crowdJPARepository.findById(crowdIdDelete).orElse(null);
//        assertNotNull(deletedCrowd);
//        assertTrue(deletedCrowd.isDeleted());
//
//        assertEquals(savedCrowd.getCrowdTitle(), deletedCrowd.getCrowdTitle());
//    }

}
