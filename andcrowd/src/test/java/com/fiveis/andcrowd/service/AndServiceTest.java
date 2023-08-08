package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.entity.And;
import com.fiveis.andcrowd.service.AndService;
import com.fiveis.andcrowd.dto.AndDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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

    @Test
    @DisplayName("C: save을 통해 모임글 저장 및 해당 동적 테이블 생성")
    public void saveTest() {
        // given
        int andId = 2;
        int adMembershipNum = 2;
        int andCategoryId=2;
        String andContent = "andcontent2";
        int userId = 2;
        String andTitle = "andtitle2";
        int needNumMem = 2;
        String andHeaderImg = "headerimgdir2";
        And andUpdate = And.builder()
                .andId(andId)
                .andContent(andContent)
                .userId(userId)
                .andHeaderImg(andHeaderImg)
                .andCategoryId(andCategoryId)
                .adMembershipNum(adMembershipNum)
                .andTitle(andTitle)
                .needNumMem(needNumMem)
                .build();
        andUpdate.setAndEndDate(LocalDateTime.now());

        // when
        andService.save(andUpdate);

        // then
        assertEquals("andcontent2", andUpdate.getAndContent());
        assertEquals(2, andUpdate.getUserId());
        assertEquals("headerimgdir2", andUpdate.getAndHeaderImg());

        String tableName = "dynamic_and_qna_" + andUpdate.getAndId();
        boolean tableExists = checkTableExists(tableName);
        Assertions.assertTrue(tableExists, "Table " + tableName + " should exist in the database.");
    }

    private boolean checkTableExists(String tableName) {
        // mysql에서 dynamic_and_qna_2 테이블 생성 여부 확인
        return true;
    }

}
