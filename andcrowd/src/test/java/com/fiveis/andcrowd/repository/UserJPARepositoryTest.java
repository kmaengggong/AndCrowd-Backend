package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserJPARepositoryTest {
    @Autowired
    UserJPARepository userJPARepository;

    // 테스트 실행 전 한 번만 실행
//    @Test
//    @BeforeTestClass
//    @DisplayName("C: 테스트 유저 저장")
//    public void saveTest(){
//        // Given
//        String userEmail = "asdf@gmail.com";
//        String userPassword = "qwer";
//        String userKorName = "김명호";
//        String userNickname = "NICK";
//        String userPhone = "010-1234-5678";
//        LocalDateTime userBirth = LocalDateTime.of(1997, 2, 14, 0, 0, 0);
//        LocalDateTime userRegister = LocalDateTime.now();
//        int userTos = 1;
//        int userPrivacy = 1;
//        int userMarketing = 1;
//        Authority authority = Authority.ROLE_ADMIN;
//
//        // When
//        User user = User.builder()
//                .userEmail(userEmail)
//                .userPassword(userPassword)
//                .userKorName(userKorName)
//                .userNickname(userNickname)
//                .userPhone(userPhone)
//                .userBirth(userBirth)
//                .userRegister(userRegister)
//                .userTos(userTos)
//                .userPrivacy(userPrivacy)
//                .userMarketing(userMarketing)
//                .authority(authority)
//                .build();
//        userJPARepository.save(user);
//
//        // Then
//    }

    @Test
    @Transactional
    @DisplayName("R: userName='김명호' 유저 조회")
    public void findByUserKorNameTest(){
        // Given
        String userName = "김명호";

        // When
        List<User> userList = userJPARepository.findAllByUserKorName(userName);

        // Then
        for(User user : userList) {
            Assertions.assertEquals(userName, user.getUserKorName());
        }
    }

    @Test
    @Transactional
    @DisplayName("R: userEmail='asdf@gmail.com' 유저 조회")
    public void findByUserEmailTest(){
        // Given
        String userEmail = "asdf@gmail.com";

        // When
        User user = userJPARepository.findByUserEmail(userEmail).get();

        // Then
        Assertions.assertEquals(userEmail, user.getUserEmail());
    }

    @Test
    @Transactional
    @DisplayName("R: userEmail='asdf@gmail.com' 유저 조회")
    public void findByUserNicknameTest(){
        // Given
        String userNickname = "NICK";

        // When
        User user = userJPARepository.findByUserNickname(userNickname).get();

        // Then
        Assertions.assertEquals(userNickname, user.getUserNickname());
    }

    @Test
    @Transactional
    @DisplayName("D: userEmail='asdf@gmail.com' 유저 삭제")
    public void deleteByUserEmailTest(){
        // Given
        String userEmail = "asdf@gmail.com";

        // When
        userJPARepository.deleteByUserEmail(userEmail);

        // Then
        Assertions.assertEquals(Optional.empty(), userJPARepository.findByUserEmail(userEmail));
    }
}
