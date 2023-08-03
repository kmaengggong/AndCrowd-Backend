package com.fiveis.andcrowd.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
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

    private String userProfileImage;  // 프로필 이미지 경로

    @Column(nullable = false)
    private Date userBirth;  // 생년월일

    @Column(nullable = false)
    // @ColumnDefault("current_time")
    private Date userRegister;  // 가입일

    @Column(nullable = false)
    private int userTos;  // 서비스 이용약관 동의

    @Column(nullable = false)
    private int userPrivacy;  // 개인정보 동의

    @Column(nullable = false)
    private int userMarketing;  // 마케팅 동의

    @ColumnDefault("FALSE")
    private boolean isAdmin;  // 관리자 권한 여부
}
