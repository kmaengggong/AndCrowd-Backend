package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserJPARepositoryTest {
    @Autowired
    UserJPARepository userJPARepository;

    String userEmail = "email@email.com";
    String userPassword = "password";
    String userName = "name";
    String userNickname = "nickname";
    String userPhone = "phone";
    Date userBirth = new Date(1997, 2, 14);
    Date userRegister = new Date(2023, 8, 2);
    int userTos = 1;
    int userPrivacy = 1;
    int userMarketing = 1;
    boolean isAdmin = true;

    @Test
    @Order(1)
    // @Transactional
    @DisplayName("C: 1번 유저 저장")
    public void saveTest(){
        // Given
        int userId = 1;

        // When
        User user = User.builder()
                .userEmail(userEmail)
                .userPassword(userPassword)
                .userName(userName)
                .userNickname(userNickname)
                .userPhone(userPhone)
                .userBirth(userBirth)
                .userRegister(userRegister)
                .userTos(userTos)
                .userPrivacy(userPrivacy)
                .userMarketing(userMarketing)
                .isAdmin(isAdmin)
                .build();
        userJPARepository.save(user);

        // Then
        Assertions.assertNotNull(userJPARepository.findById(userId));
    }

    @Test
    @Order(2)
    // @Transactional
    @DisplayName("R: 1번 유저 조회")
    public void findByIdTest(){
        // Given
        int userId = 1;

        // When
        List<User> allUser = userJPARepository.findAll();
        System.out.println(allUser);
        Optional<User> optionalUser = userJPARepository.findById(userId);
        User user = optionalUser.get();

        // Then
        Assertions.assertEquals(userId, user.getUserId());
        Assertions.assertEquals(userEmail, user.getUserEmail());
        Assertions.assertEquals(userPassword, user.getUserPassword());
        Assertions.assertEquals(userName, user.getUserName());
        Assertions.assertEquals(userNickname, user.getUserNickname());
        Assertions.assertEquals(userPhone, user.getUserPhone());
        Assertions.assertTrue(user.isAdmin());
    }

//    @Test
//    // @Transactional
//    @DisplayName("U: 1번 유저 변경")
//    public void updateTest(){
//        // Given
//        int userId = 1;
//        String userPassword = "newPassword";
//        String userNickname = "newNickname";
//        String userPhone = "newPhone";
//        String userProfileImage = "newProfileImage";
//
//        UserUpdateDTO userUpdateDTO = UserUpdateDTO.builder()
//                .userId(userId)
//                .userPassword(userPassword)
//                .userNickname(userNickname)
//                .userPhone(userPhone)
//                .userProfileImage(userProfileImage)
//                .build();
//
//        // When
//        userRepository.update(userUpdateDTO);
//
//        // Then
//        Optional<User> optionalUser = userRepository.findById(userId);
//        User user = optionalUser.get();
//
//        Assertions.assertEquals(userPassword, user.getUserPassword());
//        Assertions.assertEquals(userNickname, user.getUserNickname());
//        Assertions.assertEquals(userPhone, user.getUserPhone());
//        Assertions.assertEquals(userProfileImage, user.getUserProfileImage());
//    }

    @Test
    @Order(3)
    // @Transactional
    @DisplayName("D: 1번 유저 삭제")
    public void deleteByIdTest(){
        // Given
        int userId = 1;

        // When
        userJPARepository.deleteById(userId);

        // Then
        Assertions.assertEquals(Optional.empty(), userJPARepository.findById(userId));
    }
}
