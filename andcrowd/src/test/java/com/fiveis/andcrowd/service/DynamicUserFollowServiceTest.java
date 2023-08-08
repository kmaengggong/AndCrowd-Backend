package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserFollowDTO;
import com.fiveis.andcrowd.entity.DynamicUserFollow;
import com.fiveis.andcrowd.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class DynamicUserFollowServiceTest {
    @Autowired
    DynamicUserFollowService dynamicUserFollowService;

    String userEmail = "asdf@gmail.com";
    String tableName = "user_follow_" + userEmail.replace('@', '_').replace('.', '_');

    @Test
    @Transactional
    @DisplayName("")
    public void findAllTest(){
        String userNickname = "NICK";
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
        dynamicUserFollowService.save(map);

        List<User> userList = dynamicUserFollowService.findAll(tableName);

        // Then
        for(User user : userList){
            Assertions.assertEquals(userNickname, user.getUserNickname());
        }
    }

    @Test
    @Transactional
    @DisplayName("")
    public void saveTest(){
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
        dynamicUserFollowService.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uFollowId", uFollowId
        );
        DynamicUserFollowDTO.Find find = dynamicUserFollowService.findById(map1);

        // Then
        Assertions.assertEquals(uFollowId, find.getUFollowId());
    }

    @Test
    @Transactional
    @DisplayName("")
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
        dynamicUserFollowService.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uFollowId", uFollowId
        );
        dynamicUserFollowService.deleteById(map1);

        DynamicUserFollowDTO.Find find = dynamicUserFollowService.findById(map1);

        // Then
        Assertions.assertNull(dynamicUserFollowService.findById(map1));
    }
}
