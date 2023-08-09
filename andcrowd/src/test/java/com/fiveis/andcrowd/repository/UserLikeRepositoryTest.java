package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.Crowd;
import com.fiveis.andcrowd.entity.DynamicUserLike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserLikeRepositoryTest {
    @Autowired
    UserLikeRepository userLikeRepository;

    @Test
    @DisplayName("C")
    public void saveTest(){
        // Given
        int uLikeId = 1;
        int projectId = 1;
        int uLikeKind = 0;
        DynamicUserLike userLike = DynamicUserLike.builder()
                .projectId(projectId)
                .uLikeKind(uLikeKind)
                .build();

        // When
        userLikeRepository.save(userLike);
        Crowd crowd = userLikeRepository.findById(uLikeId);

        // Then
        Assertions.assertEquals(projectId, crowd.getCrowdId());

    }

    @Test
    @DisplayName("R")
    public void findAllTest(){
        // Given
        System.out.println("wtf");
        // When
        List<Crowd> userLikeList = userLikeRepository.findAll();

        // Then
        Assertions.assertEquals("a", "a");
    }

}
