package com.fiveis.andcrowd.controller.crowd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiveis.andcrowd.repository.crowd.DynamicCrowdBoardRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DynamicCrowdBoardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DynamicCrowdBoardRepository dynamicCrowdBoardRepository;

    @BeforeEach
    public void setMockMvc(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Transactional
    @DisplayName("crowd 1번글의 board 전체글 조회시 2번째 요소의 title은 1번글제목, content는 1번글본문 이다.")
    void findAll() throws Exception {
        // given
        int crowdId = 1;
        String title = "1번글제목";
        String content = "1번글본문";
        String url = "/crowd/1/board/all";

        // when
        final ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].crowdBoardTitle").value(title))
                .andExpect(jsonPath("$[2].crowdBoardContent").value(content));
    }
}
