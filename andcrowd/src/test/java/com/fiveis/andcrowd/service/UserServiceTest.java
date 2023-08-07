package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.UserDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    @Transactional
    @DisplayName("")
    public void findUserKorNameTest(){
        // Given
        String userKorName = "김명호";

        // When
        List<UserDTO.FindAsUser> userFindDTOList = userService.findAllByUserKorName(userKorName);

        // Then
        for(UserDTO.FindAsUser userFindDTO : userFindDTOList){
            Assertions.assertEquals(userKorName, userFindDTO.getUserKorName());
        }
    }

    @Test
    @Transactional
    @DisplayName("")
    public void findByUserEmailTest(){
        // Given
        String userEmail = "asdf@gmail.com";

        // When
        UserDTO.FindAsUser userFindDTO = userService.findByUserEmail(userEmail);

        // Then
        Assertions.assertEquals(userEmail, userFindDTO.getUserEmail());
    }

    @Test
    @Transactional
    @DisplayName("")
    public void findByUserNicknameTest(){
        // Given
        String userNickname = "NICK";

        // When
        UserDTO.FindAsPublic userFindDTO = userService.findByUserNickname(userNickname);

        // Then
        Assertions.assertEquals(userNickname, userFindDTO.getUserNickname());
    }

    @Test
    @Transactional
    @DisplayName("")
    public void deleteByUserEmail(){
        // Given
        String userEmail = "asdf@gmail.com";

        // When
        userService.deleteByUserEmail(userEmail);

        // Then
        Assertions.assertNull(userService.findByUserEmail(userEmail));
    }

    @Test
    @Transactional
    @DisplayName("")
    public void updateTest(){
        // Given
        String userEmail = "asdf@gmail.com";
        String userPassword = "changedPassword";
        String userNickname = "changedNickname";
        String userPhone = "010-0000-0000";
        String userProfileImg = "changedProfileImg";
        UserDTO.Update userUpdateDTO = UserDTO.Update.builder()
                .userEmail(userEmail)
                .userPassword(userPassword)
                .userNickname(userNickname)
                .userPhone(userPhone)
                .userProfileImg(userProfileImg)
                .build();

        // When
        userService.update(userUpdateDTO);

        // Then
        UserDTO.FindAsUser userFindDTO = userService.findByUserEmail(userEmail);
        Assertions.assertEquals(userEmail, userFindDTO.getUserEmail());
        Assertions.assertEquals(userNickname, userFindDTO.getUserNickname());
        Assertions.assertEquals(userProfileImg, userFindDTO.getUserProfileImg());
    }
}
