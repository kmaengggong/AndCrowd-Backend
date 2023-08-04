package com.fiveis.andcrowd.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AndJPARepositoryTest {

    @Autowired
    AndJPARepository andJPARepository;

//    @Test
//    @Transactional
//    @DisplayName("R: findbyId를 통해 1번 모임글 조회")
//    public void findById(){
//        // given
//        int andId = 1;
//
//        // when
//        AndFindByIdDTO andFindByIdDTO = andJPARepository.findById(andId).get();
//
//        // then
//
//    }

}
