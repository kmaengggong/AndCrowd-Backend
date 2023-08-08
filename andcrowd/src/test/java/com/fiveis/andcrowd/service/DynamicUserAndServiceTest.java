package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserAndDTO;
import com.fiveis.andcrowd.entity.DynamicUserAnd;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class DynamicUserAndServiceTest {
    @Autowired
    DynamicUserAndService dynamicUserAndService;

    String userEmail = "asdf@gmail.com";
    String tableName = "user_and_" + userEmail.replace('@', '_').replace('.', '_');

    @Test
    @Transactional
    @DisplayName("")
    public void findAllTest(){
        // Given
        // When
        List<?> findList = dynamicUserAndService.findAll(tableName);

        // Then
//        Assertions.assertTrue(findList.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("")
    public void saveTest(){
        // Given
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
        dynamicUserAndService.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uAndId", uAndId
        );
        dynamicUserAndService.deleteById(map1);

        // Then
        Assertions.assertNull(dynamicUserAndService.findById(map1));
    }

    @Test
    @Transactional
    @DisplayName("")
    public void deleteByIdTest(){
        // Given
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
        dynamicUserAndService.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uAndId", uAndId
        );
        dynamicUserAndService.deleteById(map1);

        DynamicUserAndDTO.Find find = dynamicUserAndService.findById(map1);

        // Then
        Assertions.assertNull(dynamicUserAndService.findById(map1));
    }
}
