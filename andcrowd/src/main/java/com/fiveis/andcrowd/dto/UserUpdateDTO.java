package com.fiveis.andcrowd.dto;

import lombok.*;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class UserUpdateDTO {
    private int userId;
    private String userPassword;
    private String userNickname;
    private String userPhone;
    private String userProfileImage;
}
