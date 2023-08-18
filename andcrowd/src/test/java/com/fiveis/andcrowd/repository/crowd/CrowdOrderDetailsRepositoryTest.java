package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CrowdOrderDetailsRepositoryTest {

    @Autowired
    private CrowdOrderDetailsJPARepository crowdOrderDetailsJPARepository;

    @Test
    @Transactional
    public void FindAllTest(){
        List<CrowdOrderDetails> crowdOrderDetailsList = crowdOrderDetailsJPARepository.findAll();

        assertEquals(3, crowdOrderDetailsList.size());
    }
}
