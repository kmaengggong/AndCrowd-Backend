package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdSponsorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DynamicCrowdSponsorServiceTest {
    @Autowired
    DynamicCrowdSponsorService dynamicCrowdSponsorService;

    @Test
    @Transactional
    public void findAllTest() {
        // given
        int crowdId = 456;

        // when
        List<DynamicCrowdSponsorDTO.FindById> findAllList = dynamicCrowdSponsorService.findAll(crowdId);

        // then
        assertEquals(3, findAllList.size());
    }

    @Test
    @Transactional
    public void findByCrowdSponsorIdTest() {
        // given
        int sponsorId = 1;
        int crowdId = 456;
        // when
        DynamicCrowdSponsorDTO.FindById crowdSponsor = dynamicCrowdSponsorService.findBySponsorId(crowdId,sponsorId);
        // then
        assertEquals(1, crowdSponsor.getSponsorId());
        assertEquals(100, crowdSponsor.getPurchaseId());
    }

    @Test
    @Transactional
    public void saveTest() {
        // given
        int sponsorId = 4;
        int crowdId = 456;
        int purchaseId = 400;

        DynamicCrowdSponsorDTO.Update crowdSponsorSave = DynamicCrowdSponsorDTO.Update.builder()
                .sponsorId(sponsorId)
                .crowdId(crowdId)
                .purchaseId(purchaseId)
                .build();

        // when
        dynamicCrowdSponsorService.save(crowdSponsorSave);
        List<DynamicCrowdSponsorDTO.FindById> sponsorList = dynamicCrowdSponsorService.findAll(crowdId);
        DynamicCrowdSponsorDTO.FindById saveSponsor = sponsorList.get(0);

        // then
        assertEquals(sponsorId, crowdSponsorSave.getSponsorId());
        assertEquals(purchaseId, crowdSponsorSave.getPurchaseId());
    }

//    @Test
//    @Transactional
//    public void updateTest() {
//        // given
//        int sponsorId = 3;
//        int crowdId = 456;
//        int purchaseId = 250;
//
//        DynamicCrowdSponsorDTO.Update updateSponsor = DynamicCrowdSponsorDTO.Update.builder()
//                .sponsorId(sponsorId)
//                .crowdId(crowdId)
//                .purchaseId(purchaseId)
//                .build();
//        // when
//        dynamicCrowdSponsorService.update(updateSponsor);
//        DynamicCrowdSponsorDTO.Update savedSponsor = dynamicCrowdSponsorService.findBySponsorId(crowdId, sponsorId);
//        // then
//        assertEquals(purchaseId, savedSponsor.getPurchaseId());
//    }

    @Test
    @Transactional
    public void deleteSponsorTest() {
        // given
        int sponsorId = 3;
        int crowdId = 456;
        // when
        dynamicCrowdSponsorService.deleteByCrowdSponsorId(crowdId, sponsorId);
        // then
        assertTrue(dynamicCrowdSponsorService.findBySponsorId(crowdId, sponsorId).isDeleted());
    }
}