package com.fiveis.andcrowd.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.enums.Authority;
import com.fiveis.andcrowd.repository.user.UserJPARepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DynamicUserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    DynamicUserController dynamicUserController;
    @Autowired
    UserJPARepository userJPARepository;

    @BeforeEach
    public void setMockMvcAndSaveUser(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        int userId = 1;
        String userEmail = "asdf@gmail.com";
        String userPassword = "qwer";
        String userKorName = "김명호";
        String userNickname = "NICK";
        String userPhone = "010-1234-5678";
        LocalDateTime userBirth = LocalDateTime.of(1997, 2, 14, 0, 0, 0);
        LocalDateTime userRegister = LocalDateTime.now();
        int userTos = 1;
        int userPrivacy = 1;
        int userMarketing = 1;
        Authority authority = Authority.ROLE_ADMIN;

        userJPARepository.save(User.builder()
                .userId(userId)
                .userEmail(userEmail)
                .userPassword(userPassword)
                .userKorName(userKorName)
                .userNickname(userNickname)
                .userPhone(userPhone)
                .userBirth(userBirth)
                .userRegister(userRegister)
                .userTos(userTos)
                .userPrivacy(userPrivacy)
                .userMarketing(userMarketing)
                .authority(authority)
                .build());
    }

    @Test
    @Transactional
    @DisplayName("1번 유저가 참가한 모임 목록 조회")
    public void getUserAndTest() throws Exception{
        // Given
        String url = "/user/1/and";

        // When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk());
    }
}
