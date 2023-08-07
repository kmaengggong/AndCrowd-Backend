package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.Authority;
import lombok.*;

import java.time.LocalDateTime;

public class UserDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserFindByIdDTO{
        private String userId;
        private String userEmail;
        private String userPassword;
        private String userKorName;
        private String userNickname;
        private String userPhone;
        private String userProfileImg;
        private LocalDateTime userBirth;
        private LocalDateTime userRegister;
        private Authority authority;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserFindDTO{
        private String userEmail;
        private String userNickname;
        private String userProfileImg;
        private LocalDateTime userRegister;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserUpdateDTO{
        private String userEmail;
        private String userPassword;
        private String userNickname;
        private String userPhone;
        private String userProfileImg;
    }
}
