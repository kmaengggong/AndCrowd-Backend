package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.enums.Authority;
import lombok.*;

import java.time.LocalDateTime;

public class UserDTO {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindAsPublic{
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
    public static class FindAsUser{
        private String userEmail;
        private String userPassword;
        private String userKorName;
        private String userNickname;
        private String userPhone;
        private String userProfileImg;
        private LocalDateTime userBirth;
        private LocalDateTime userRegister;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindAsAdmin{
        private int userId;
        private String userEmail;
        private String userPassword;
        private String userKorName;
        private String userNickname;
        private String userPhone;
        private String userProfileImg;
        private LocalDateTime userBirth;
        private LocalDateTime userRegister;
        private int userTos;
        private int userPrivacy;
        private int userMarketing;
        private Authority authority;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        private String userEmail;
        private String userPassword;
        private String userNickname;
        private String userPhone;
        private String userProfileImg;
    }
}
