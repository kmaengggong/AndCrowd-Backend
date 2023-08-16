package com.fiveis.andcrowd.repository.etc;

import com.fiveis.andcrowd.entity.etc.AdPayment;
import com.fiveis.andcrowd.repository.etc.AdPaymentJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AdPaymentJPARepositoryTest {
    @Autowired
    AdPaymentJPARepository adPaymentJPARepository;

    @Test
    @BeforeEach
    @DisplayName("C: ")
    public void saveTest(){
        // Given
        int adPaymentId = 1;
        int userId = 1;
        int projectId = 1;
        int projectType = 0;
        LocalDateTime purchasedAt = LocalDateTime.now();
        LocalDateTime expiredAt = LocalDateTime.now();
        int adPaymentStatus = 0;

        // When
        AdPayment adPayment = AdPayment.builder()
                .adPaymentId(adPaymentId)
                .userId(userId)
                .projectId(projectId)
                .projectType(projectType)
                .purchasedAt(purchasedAt)
                .expiredAt(expiredAt)
                .adPaymentStatus(adPaymentStatus)
                .build();

        adPaymentJPARepository.save(adPayment);

        // Then
    }

    // JPA 이기 때문에 기타 기능 테스트 생략. 후에 기능 생기면 추가할 예정
}
