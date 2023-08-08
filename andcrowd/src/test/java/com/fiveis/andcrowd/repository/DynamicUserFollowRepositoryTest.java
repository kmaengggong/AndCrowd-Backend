package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserFollowDTO;
import com.fiveis.andcrowd.entity.DynamicUserFollow;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class DynamicUserFollowRepositoryTest {
    @Autowired
    DynamicUserFollowRepository dynamicUserFollowRepository;

    String userEmail = "asdf@gmail.com";
    String tableName = "user_follow_" + userEmail.replace('@', '_').replace('.', '_');

    @BeforeEach
    public void createDynamicUserFollowTableTest(){
        // Given
        // When
        dynamicUserFollowRepository.createDynamicUserFollowTable(tableName);

        // Then
    }

    @Test
    @Transactional
    @DisplayName("CR: ")
    public void saveFindByIdTest(){
        // Given
        int uFollowId = 1;
        int userId = 1;
        DynamicUserFollow dynamicUserFollow = DynamicUserFollow.builder()
                .uFollowId(uFollowId)
                .userId(userId)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserFollow", dynamicUserFollow
        );
        dynamicUserFollowRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uFollowId", uFollowId
        );
        DynamicUserFollowDTO.Find find = dynamicUserFollowRepository.findById(map1);

        // Then
        Assertions.assertEquals(uFollowId, find.getUFollowId());
    }

    @Test
    @Transactional
    @DisplayName("R: ")
    public void findAllTest(){
        // Given
        // When
        List<DynamicUserFollowDTO.Find> findList = dynamicUserFollowRepository.findAll(tableName);

        // Then
        Assertions.assertTrue(findList.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("CRD: ")
    public void deleteByIdTest(){
        // Given
        int uFollowId = 1;
        int userId = 1;
        DynamicUserFollow dynamicUserFollow = DynamicUserFollow.builder()
                .uFollowId(uFollowId)
                .userId(userId)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserFollow", dynamicUserFollow
        );
        dynamicUserFollowRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uFollowId", uFollowId
        );
        dynamicUserFollowRepository.deleteById(map1);

        // Then
        Assertions.assertNull(dynamicUserFollowRepository.findById(map1));
    }
}
