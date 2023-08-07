package com.fiveis.andcrowd.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrowdRepositoryTest {

    @Autowired
    CrowdJPARepository crowdJPARepository;
}
