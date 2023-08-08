package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicAndQnaDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DynamicAndQnaRepositoryTest {

    @Autowired
    DynamicAndQnaRepository dynamicAndQnaRepository;

    @Autowired
    AndDynamicRepository andDynamicRepository;

    @BeforeEach
    public void setAndQnaTable(){
        dynamicAndQnaRepository.createAndQnaTable();
        dynamicAndQnaRepository.insertTestData();
    }

    @AfterEach
    public void dropAndQnaTable() {
        dynamicAndQnaRepository.dropAndQnaTable();
    }

    @Test
    @DisplayName("R: findByAndQnaId를 통해 개별 글 조회")
    public void findByAndQnaIdTest(){
        //given
        int andQnaId = 2;
        int andId = 321;

        // when
        DynamicAndQnaDTO.FindById andQna = dynamicAndQnaRepository.findByAndQnaId(andId, andQnaId);

        // then
        assertEquals(2, andQna.getAndQnaId());
        assertEquals("QnA Title 2", andQna.getAndQnaTitle());
        assertEquals("QnA Content 2", andQna.getAndQnaContent());
        assertEquals(321, andQna.getAndId());
    }
}
