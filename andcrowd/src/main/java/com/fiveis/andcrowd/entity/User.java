package com.fiveis.andcrowd.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;  // 유저 ID

    @Column(nullable = false)  // 이메일
    private String userEmail;

    @Column(nullable = false)  // 비밀번호
    private String userPassword;

    @Column(nullable = false)
    private String userName;  // 이름

    @Column(nullable = false)
    private String userNickname;  // 닉네임

    @Column(nullable = false)
    private String userPhone;  // 전화번호

    private String userProfileImg;  // 프로필 이미지 경로

    @Column(nullable = false)
    private Date userBirth;  // 생년월일

    @Column(nullable = false)
    private Date userRegister;  // 가입일

    @Column(nullable = false)
    private int userTos;  // 서비스 이용약관 동의

    @Column(nullable = false)
    private int userPrivacy;  // 개인정보 동의

    @Column(nullable = false)
    private int userMarketing;  // 마케팅 동의

    @ColumnDefault("FALSE")
    private Authority authority;  // 관리자 권한 여부

    @Builder
    public User(int userId, String userEmail, String userPassword, String userName, String userNickname,
                String userPhone, String userProfileImg, Date userBirth, Date userRegister,
                int userTos, int userPrivacy, int userMarketing, Authority authority){
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPhone = userPhone;
        this.userProfileImg = userProfileImg;
        this.userBirth = userBirth;
        this.userRegister = userRegister;
        this.userTos = userTos;
        this.userPrivacy = userPrivacy;
        this.userMarketing = userMarketing;
        this.authority = authority;
    }

    @PrePersist
    public void setDefaultValue(){
        this.userRegister = new Date(new java.util.Date().getTime());
    }

    public void updateUser(String userPassword, String userPhone, String userProfileImg){
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userProfileImg = userProfileImg;
    }
}
