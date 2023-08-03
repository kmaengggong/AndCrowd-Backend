package com.fiveis.andcrowd.dto;

import lombok.*;

import java.sql.Date;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class UserFindByIdDTO {
    private int userId;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userNickname;
    private String userPhone;
    private String userProfileImage;
    private Date userBirth;
    private Date userRegister;
    private int userTos;
    private int userPrivacy;
    private int userMarketing;
    private boolean isAdmin;
}
