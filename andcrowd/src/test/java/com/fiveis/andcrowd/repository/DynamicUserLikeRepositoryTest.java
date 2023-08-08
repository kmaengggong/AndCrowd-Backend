package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserLikeDTO;
import com.fiveis.andcrowd.entity.DynamicUserLike;
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
public class DynamicUserLikeRepositoryTest {
    @Autowired
    DynamicUserLikeRepository dynamicUserLikeRepository;

    String userEmail = "asdf@gmail.com";
    String tableName = "user_like_" + userEmail.replace('@', '_').replace('.', '_');

    @BeforeEach
    public void createDynamicUserLikeTableTest(){
        // Given
        // When
        dynamicUserLikeRepository.createDynamicUserLikeTable(tableName);

        // Then
    }

    @Test
    @Transactional
    @DisplayName("CR: ")
    public void saveTest(){
        // Given
        int uLikeId = 1;
        int projectId = 1;
        int projectType = 0;
        DynamicUserLike dynamicUserLike = DynamicUserLike.builder()
                .uLikeId(uLikeId)
                .projectId(projectId)
                .projectType(projectType)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserLike", dynamicUserLike
        );
        dynamicUserLikeRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uLikeId", uLikeId
        );
        DynamicUserLikeDTO.Find find = dynamicUserLikeRepository.findById(map1);

        // Then
        Assertions.assertEquals(uLikeId, find.getULikeId());
    }

    @Test
    @Transactional
    @DisplayName("R: ")
    public void findAllTest(){
        // Given
        // When
        List<DynamicUserLikeDTO.Find> findList = dynamicUserLikeRepository.findAll(tableName);

        // Then
        Assertions.assertTrue(findList.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("CRD: ")
    public void deleteByIdTest(){
        // Given
        int uLikeId = 1;
        int projectId = 1;
        int projectType = 0;
        DynamicUserLike dynamicUserLike = DynamicUserLike.builder()
                .uLikeId(uLikeId)
                .projectId(projectId)
                .projectType(projectType)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserLike", dynamicUserLike
        );
        dynamicUserLikeRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uLikeId", uLikeId
        );
        dynamicUserLikeRepository.deleteById(map1);

        // Then
        Assertions.assertNull(dynamicUserLikeRepository.findById(map1));
    }
}
