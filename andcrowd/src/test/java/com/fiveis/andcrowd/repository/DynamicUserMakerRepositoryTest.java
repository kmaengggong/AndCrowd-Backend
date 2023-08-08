package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserMakerDTO;
import com.fiveis.andcrowd.entity.DynamicUserMaker;
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
public class DynamicUserMakerRepositoryTest {
    @Autowired
    DynamicUserMakerRepository dynamicUserMakerRepository;

    String userEmail = "asdf@gmail.com";
    String tableName = "user_maker_" + userEmail.replace('@', '_').replace('.', '_');

    @BeforeEach
    public void createDynamicUserMakerTableTest(){
        // Given
        // When
        dynamicUserMakerRepository.createDynamicUserMakerTable(tableName);

        // Then
    }

    @Test
    @Transactional
    @DisplayName("CR: 1번 모임글 생성 후 조회")
    public void saveTest(){
        // Given
        int uMakerId = 1;
        int projectId = 1;
        int projectType = 0;
        DynamicUserMaker dynamicUserMaker = DynamicUserMaker.builder()
                .uMakerId(uMakerId)
                .projectId(projectId)
                .projectType(projectType)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserMaker", dynamicUserMaker
        );
        dynamicUserMakerRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uMakerId", uMakerId
        );
        DynamicUserMakerDTO.Find find = dynamicUserMakerRepository.findById(map1);

        // Then
        Assertions.assertEquals(uMakerId, find.getUMakerId());
    }

    @Test
    @Transactional
    @DisplayName("R: 모든 생성글 조회")
    public void findAllTest(){
        // Given
        // When
        List<DynamicUserMakerDTO.Find> findList = dynamicUserMakerRepository.findAll(tableName);

        // Then
        Assertions.assertTrue(findList.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("CRD: 1번 모임글 생성 후 삭제 후 조회")
    public void deleteByIdTest(){
        // Given
        int uMakerId = 1;
        int projectId = 1;
        int projectType = 0;
        DynamicUserMaker dynamicUserMaker = DynamicUserMaker.builder()
                .uMakerId(uMakerId)
                .projectId(projectId)
                .projectType(projectType)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserMaker", dynamicUserMaker
        );
        dynamicUserMakerRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uMakerId", uMakerId
        );
        dynamicUserMakerRepository.deleteById(map1);

        // Then
        Assertions.assertNull(dynamicUserMakerRepository.findById(map1));
    }
}
