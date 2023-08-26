package com.fiveis.andcrowd.controller.crowd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import com.fiveis.andcrowd.repository.crowd.CrowdOrderDetailsJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CrowdOrderDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CrowdOrderDetailsJPARepository crowdOrderDetailsJPARepository;

    @BeforeEach
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Transactional
    void findAllListTest() throws Exception {
        // given
        String url = "/crowd_order/list";
        String purchaseName = "김자바";
        // when
        final ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));
        // then
        resultActions.andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].purchaseName").value(purchaseName));

        System.out.println(resultActions);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    void insertOrderTest() throws Exception {
        // given
        int crowdId = 4;
        int purchaseId = 4;
        int userId = 4;
        int rewardId = 4;
        int sponsorId = 4;
        String purchaseName = "임토비";
        String purchasePhone = "01012345678";
        String purchaseAddress = "광주 광산구";
        String purchaseStatus = "계좌송금";
        LocalDateTime purchaseDate = LocalDateTime.now();

        CrowdOrderDetails newOrder = CrowdOrderDetails.builder().build();

        System.out.println(newOrder);

        String url = "/crowd_order/successorder/4";
        String url2 = "/crowd_order/list";
        // when
        final String jsonOrder = objectMapper.writeValueAsString(newOrder);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonOrder));
        // then
        final ResultActions result = mockMvc.perform(get(url2)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(jsonPath("$[3].crowdId").value(crowdId))
                .andExpect(jsonPath("$[3].purchaseId").value(purchaseId));
    }

    @Test
    @Transactional
    void findByPurchaseIdTest() throws Exception {
        // given
        int purchaseId = 3;
        String purchaseName = "김코드";
        String url = "/crowd_order/3";
        // when
        final ResultActions getResult = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));
        // then
        getResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.purchaseName").value(purchaseName));
    }

    @Test
    @Transactional
    void updateOrder() throws Exception {
        // given
        // when
        // then
    }

    @Test
    @Transactional
    void deleteOrder() throws Exception {
        // given
        // when
        // then
    }
}