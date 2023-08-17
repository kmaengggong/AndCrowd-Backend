package com.fiveis.andcrowd.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiveis.andcrowd.entity.and.And;
import com.fiveis.andcrowd.entity.user.*;
import com.fiveis.andcrowd.enums.Authority;
import com.fiveis.andcrowd.service.and.AndService;
import com.fiveis.andcrowd.service.user.*;
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
    AndService andService;
    @Autowired
    DynamicUserController dynamicUserController;
    @Autowired
    DynamicUserAndService dynamicUserAndService;
    @Autowired
    DynamicUserFollowService dynamicUserFollowService;
    @Autowired
    DynamicUserLikeService dynamicUserLikeService;
    @Autowired
    DynamicUserMakerService dynamicUserMakerService;
    @Autowired
    DynamicUserOrderService dynamicUserOrderService;

    // User 테스트 데이터
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

    // And 테스트 데이터
    int andId = 1;

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

        // And 테이블 중복 생성 및 중복 데이터 추가 시 오류
//        andService.save(And.builder()
//                        .andId(andId)
//                        .userId(userId)
//                        .andCategoryId(0)
//                        .crowdId(0)
//                        .andTitle("andTitle")
//                        .andHeaderImg("andHeaderImg")
//                        .andContent("andContent")
//                        .andEndDate(LocalDateTime.now())
//                        .needNumMem(0)
//                        .andLikeCount(0)
//                        .andViewCount(0)
//                        .andStatus(0)
//                        .adId(0)
//                        .build());
    }

    @Test
    @Transactional
    @DisplayName("1번 유저가 참가한 모임(1번 모임 추가 후) 목록 조회")
    public void getUserAndTest() throws Exception{
        // Given
        String url = "/user/1/and";

        dynamicUserAndService.save(User.toTableName(userEmail),
                DynamicUserAnd.builder()
                        .uAndId(1)
                        .andId(andId)
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

        dynamicUserFollowService.save(User.toTableName(userEmail),
                DynamicUserFollow.builder()
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

        dynamicUserLikeService.save(User.toTableName(userEmail),
                DynamicUserLike.builder()
                        .uLikeId(1)
                        .projectId(andId)
                        .projectType(0)
                        .build());

        // When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].projectId").value(andId));
    }

    @Test
    @Transactional
    @DisplayName("1번 유저가 만든 프로젝트(추가 후) 목록 조회")
    public void getUserMakerTest() throws Exception{
        // Given
        String url = "/user/1/maker";

        // 원래는 Project 생성 시 자동으로 userMaker에 추가되어야 함.
        dynamicUserMakerService.save(User.toTableName(userEmail),
                DynamicUserMaker.builder()
                        .uMakerId(1)
                        .projectId(andId)
                        .projectType(0)
                        .build());

        // When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].projectId").value(andId));
    }

    @Test
    @Transactional
    @DisplayName("1번 유저의 펀딩 주문 목록 조회")
    public void getUserOrderTest() throws Exception{
        // Given
        String url = "/user/1/order";

        dynamicUserOrderService.save(User.toTableName(userEmail),
                DynamicUserOrder.builder()
                        .uOrderId(1)
                        .orderId(1)
                        .build());

        // When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(1));
    }
}
