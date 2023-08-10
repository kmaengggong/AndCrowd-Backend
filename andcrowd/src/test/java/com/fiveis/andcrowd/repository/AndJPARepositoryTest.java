package com.fiveis.andcrowd.repository;
import com.fiveis.andcrowd.dto.AndDTO;
import com.fiveis.andcrowd.entity.And;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        int andIdToDelete = 1;

        // When
        andRepository.softDeleteById(andIdToDelete);
        andRepository.flush();

        // Then
        And deletedAnd = andRepository.findById(andIdToDelete).orElse(null);
        assertNotNull(deletedAnd);
        assertTrue(deletedAnd.isDeleted());
    }
}
