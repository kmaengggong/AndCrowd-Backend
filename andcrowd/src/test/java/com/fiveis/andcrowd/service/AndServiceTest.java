package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.service.AndService;
import com.fiveis.andcrowd.dto.AndDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class AndServiceTest {

    @Autowired
    AndService andService;

    @Test
    @Transactional
    @DisplayName("R: findById를 통해 1번 모임글 조회")
    public void findById(){
        // given
        int andId = 1;
        String andContent = "andcontent";
        int userId = 1;
        String andHeaderImg = "headerimgdir";

        // when
        AndDTO.FindById andFindByIdDTO = andService.findById(andId).get();

        // then
        assertEquals(andContent, andFindByIdDTO.getAndContent());
        assertEquals(userId, andFindByIdDTO.getUserId());
        assertEquals(andHeaderImg, andFindByIdDTO.getAndHeaderImg());
    }

}
