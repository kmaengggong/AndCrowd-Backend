package com.fiveis.andcrowd.controller.crowd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiveis.andcrowd.repository.crowd.DynamicCrowdRewardRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DynamicCrowdRewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DynamicCrowdRewardRepository dynamicCrowdRewardRepository;

    @BeforeEach
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Transactional
    void findAllTest() throws Exception {
        // given
        int crowdId = 123;
        String url = "/reward/" + crowdId + "/list";
        // when
        final ResultActions resultList = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));
        // then
        resultList.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rewardTitle").value("슈퍼얼리버드1"))
                .andExpect(jsonPath("$[1].rewardTitle").value("슈퍼얼리버드2"))
                .andExpect(jsonPath("$[2].rewardTitle").value("슈퍼얼리버드3"));
    }

    @Test
    void getCrowdRewardId() {
    }

    @Test
    void createCrowdReward() {
    }

    @Test
    void updateDynamicCrowdReward() {
    }

    @Test
    void deleteDynamicReward() {
    }
}