package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CrowdOrderDetailsServiceTest {
    @Autowired
    CrowdOrderDetailsService crowdOrderDetailsService;
    @Autowired
    CrowdService crowdService;

    @Test
    @Transactional
    @DisplayName("purchaseId 3번 조회")
    public void findByIdTest() {
        // given
        int purchaseId = 3;
        // when
        CrowdOrderDetailsDTO.FindById result = crowdOrderDetailsService.findById(purchaseId);
        // then
        assertEquals(purchaseId, result.getCrowdId());
    }

    @Test
    @Transactional
    @DisplayName("결제 내역 전체 조회")
    public void findAllTest() {
        // given
        // when
        List<?> findAllList = crowdOrderDetailsService.findAll();
        // then
        assertEquals(3, findAllList.size());
    }

    @Test
    @Transactional
    @DisplayName("crowd 3번글의 후원결제시 3번글의 결제내역은 총 2개")
    public void saveTest() {
        // given
        int purchaseId = 4;
        int crowdId = 3;
        String purchaseAddress = "광주광역시 북구";
        LocalDateTime purchaseDate = LocalDateTime.now();
        String purchaseName = "김부트";
        String purchasePhone = "01012345678";
        int rewardId = 1;
        int sponsorId = 4;
        int userId = 4;
        String purchaseStatus = "결제대기";
        CrowdOrderDetails saveList = CrowdOrderDetails.builder()
                .purchaseId(purchaseId)
                .crowdId(crowdId)
                .purchaseAddress(purchaseAddress)
                .purchaseDate(purchaseDate)
                .purchaseName(purchaseName)
                .purchasePhone(purchasePhone)
                .rewardId(rewardId)
                .sponsorId(sponsorId)
                .userId(userId)
                .purchaseStatus(purchaseStatus)
                .build();

        // when
        crowdOrderDetailsService.save(saveList);
        System.out.println(saveList);
//        CrowdOrderDetailsDTO.FindById findById = crowdOrderDetailsService.findById(crowdId);
//        System.out.println(findById);
        List<CrowdOrderDetailsDTO.FindById> crowdOrders = crowdOrderDetailsService.findById();
        System.out.println(crowdOrders);

        // then
        Assertions.assertEquals(2, crowdOrders.size());
    }
}
