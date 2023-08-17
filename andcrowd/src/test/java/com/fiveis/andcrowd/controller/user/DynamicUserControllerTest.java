package com.fiveis.andcrowd.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiveis.andcrowd.entity.and.And;
import com.fiveis.andcrowd.entity.user.DynamicUserFollow;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.enums.Authority;
import com.fiveis.andcrowd.service.and.AndService;
import com.fiveis.andcrowd.service.user.DynamicUserAndService;
import com.fiveis.andcrowd.service.user.DynamicUserFollowService;
import com.fiveis.andcrowd.service.user.UserService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    UserService userService;
    @Autowired
    DynamicUserController dynamicUserController;
    @Autowired
    DynamicUserAndService dynamicUserAndService;
    @Autowired
    AndService andService;
    @Autowired
    DynamicUserFollowService dynamicUserFollowService;

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

    @BeforeEach
    public void setMockMvcAndSaveUser(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        userService.save(User.builder()
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
    @DisplayName("1번 유저가 참가한 모임(1번 모임 추가 후) 목록 조회")
    public void getUserAndTest() throws Exception{
        // Given
        String url = "/user/1/and";
        int andId = 1;

        andService.save(And.builder()
                        .andId(andId)
                        .userId(userId)
                        .
                        .build());
        // When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].andId").value(andId));
    }

    @Test
    @Transactional
    @DisplayName("1번 유저가 팔로우한 유저 목록(2번 유저 추가 후) 조회")
    public void getUserFollowTest() throws Exception{
        // Given
        String url = "/user/1/follow";
        int newUserId = 2;
        String newUserEmail = "qwer@gmail.com";

        userService.save(User.builder()
                .userId(newUserId)
                .userEmail(newUserEmail)
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

        dynamicUserFollowService.save(User.toTableName(userEmail), DynamicUserFollow.builder()
                        .uFollowId(1)
                        .userId(newUserId)
                        .build());

        // When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userNickname").value(userNickname));
    }

    @Test
    @Transactional
    @DisplayName("1번 유저가 참가한 모임 목록 조회")
    public void getUserLikeTest() throws Exception{
        // Given
        String url = "/user/1/like";

        // When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("1번 유저가 참가한 모임 목록 조회")
    public void getUserMakerTest() throws Exception{
        // Given
        String url = "/user/1/maker";

        // When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("1번 유저가 참가한 모임 목록 조회")
    public void getUserOrderTest() throws Exception{
        // Given
        String url = "/user/1/order";

        // When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk());
    }
}
