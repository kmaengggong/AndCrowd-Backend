package com.fiveis.andcrowd.repository;
import com.fiveis.andcrowd.dto.AndDTO;
import com.fiveis.andcrowd.entity.And;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AndJPARepositoryTest {

    @Autowired
    private AndJPARepository andRepository;

    @Test
    @Transactional
    public void testSoftDeleteById() {
        // Given
        And newAnd = And.builder()
                .andId(1)
                .userId(1)
                .andCategoryId(1)
                .crowdId(1)
                .andTitle("Test Title")
                .andHeaderImg("header.jpg")
                .andContent("Test Content")
                .andEndDate(LocalDateTime.now().plusDays(7))
                .needNumMem(5)
                .andLikeCount(0)
                .andViewCount(0)
                .andStatus(0)
                .adId(1)
                .build();

        andRepository.save(newAnd);
        andRepository.flush();

        int andIdToDelete = newAnd.getAndId();

        // When
        andRepository.deleteById(andIdToDelete);
        andRepository.flush();

        // Then
        And deletedAnd = andRepository.findById(andIdToDelete).orElse(null);
        assertNotNull(deletedAnd);
        assertTrue(deletedAnd.isDeleted());
    }
    }
