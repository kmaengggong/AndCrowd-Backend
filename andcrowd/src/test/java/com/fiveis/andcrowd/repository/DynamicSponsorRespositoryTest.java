package com.fiveis.andcrowd.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class DynamicSponsorRespositoryTest {

    @Autowired
    DynamicSponsorRepository dynamicSponsorRepository;

    @Test
    @Transactional
    @DisplayName("후원 Id는 '1'번일 것이다")
    public void findByIdTest(){

    }
}
