package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserAndDTO;
import com.fiveis.andcrowd.entity.DynamicUserAnd;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class DynamicUserAndRepositoryTest {
    @Autowired
    DynamicUserAndRepository dynamicUserAndRepository;

    @Test
    @DisplayName("테이블 생성")
    public void createDyanamicUserAndTableTest(){
        // Given
        String userEmail = "asdf@gmail.com";
        String tableName = "user_and_" + userEmail.replace('@', '_').replace('.', '_');

        // When
        dynamicUserAndRepository.createDynamicUserAndTable(tableName);

        // Then
    }

    @Test
    @Transactional
    @DisplayName("CR: 1번 모임글 추가 후 조회")
    public void saveFindByIdTest(){
        // Given
        String userEmail = "asdf@gmail.com";
        String tableName = "user_and_" + userEmail.replace('@', '_').replace('.', '_');
        int uAndId = 1;
        int andId = 1;
        DynamicUserAnd dynamicUserAnd = DynamicUserAnd.builder()
                .uAndId(uAndId)
                .andId(andId)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserAnd", dynamicUserAnd
        );
        dynamicUserAndRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uAndId", uAndId
        );
        DynamicUserAndDTO.Find find = dynamicUserAndRepository.findById(map1);

        // Then
        Assertions.assertEquals(uAndId, find.getUAndId());
    }

    @Test
    @Transactional
    @DisplayName("R: 모든 참여 모임글 조회")
    public void findAllTest(){
        // Given
        String userEmail = "asdf@gmail.com";
        String tableName = "user_and_" + userEmail.replace('@', '_').replace('.', '_');

        // When
        List<DynamicUserAndDTO.Find> findList = dynamicUserAndRepository.findAll(tableName);

        // Then
        Assertions.assertTrue(findList.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("CRD: 1번 글 추가 후 삭제 후 조회")
    public void deleteByIdTest(){
        // Given
        String userEmail = "asdf@gmail.com";
        String tableName = "user_and_" + userEmail.replace('@', '_').replace('.', '_');
        int uAndId = 1;
        int andId = 1;
        DynamicUserAnd dynamicUserAnd = DynamicUserAnd.builder()
                .uAndId(uAndId)
                .andId(andId)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserAnd", dynamicUserAnd
        );
        dynamicUserAndRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uAndId", uAndId
        );
        dynamicUserAndRepository.deleteById(map1);

        // Then
        Assertions.assertNull(dynamicUserAndRepository.findById(map1));
    }
}
