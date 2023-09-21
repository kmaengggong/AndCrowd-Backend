package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdMemberDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DynamicCrowdMemberRepositoryTest {
    @Autowired
    private DynamicCrowdMemberRepository dynamicCrowdMemberRepository;

    @BeforeEach
    public void setUpCrowdMember() {
        dynamicCrowdMemberRepository.createCrowdMemberTable();
        dynamicCrowdMemberRepository.insertTestData();
    }

    @AfterEach
    public void dropCrowdMember() {
        dynamicCrowdMemberRepository.dropCrowdMemberTable();
    }

    @Test
    public void findAllTest() {
        int crowdId = 1111;
        List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAllList = dynamicCrowdMemberRepository.findAll(crowdId);
        assertEquals(3,findAllList.size());
    }

    @Test
    public void findByIdTest() {
        int crowdId = 1111;
        int memberId = 1;

        DynamicCrowdMemberDTO.FindByCrowdMemberId member = dynamicCrowdMemberRepository.findByCrowdMemberId(crowdId, memberId);

        assertEquals(memberId, member.getMemberId());
        assertEquals(1111, member.getCrowdId());
        assertEquals(1, member.getUserId());
    }

    @Test
    @Transactional
    public void saveTest() {
        int crowdId = 1111;
        int memberId = 4;
        int userId = 4;

        DynamicCrowdMemberDTO.Update saveMember = DynamicCrowdMemberDTO.Update.builder()
                .crowdId(crowdId)
                .userId(userId)
                .isDeleted(false)
                .build();

        dynamicCrowdMemberRepository.save(saveMember);
        DynamicCrowdMemberDTO.FindByCrowdMemberId savedMember = dynamicCrowdMemberRepository.findByCrowdMemberId(crowdId, memberId);

        assertEquals(crowdId, savedMember.getCrowdId());
        assertEquals(userId, savedMember.getUserId());
        assertFalse(savedMember.isDeleted());
    }

    @Test
    public void deleteByIdTest() {
        int crowdId = 1111;
        int memberId = 3;

        dynamicCrowdMemberRepository.deleteById(crowdId, memberId);
        DynamicCrowdMemberDTO.FindByCrowdMemberId crowdMemberId = dynamicCrowdMemberRepository.findByCrowdMemberId(crowdId, memberId);

        assertThat(crowdMemberId.isDeleted()).isTrue();
    }
}