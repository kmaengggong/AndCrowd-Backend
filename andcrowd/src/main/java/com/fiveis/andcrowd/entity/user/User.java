package com.fiveis.andcrowd.entity.user;

import com.fiveis.andcrowd.enums.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@Table(name="users")
public class User implements UserDetails {
    // 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;  // 유저 ID

    @Column(nullable = false)  // 이메일
    private String userEmail;

    @Column(nullable = false)  // 비밀번호
    private String userPassword;

    @Column(nullable = false)
    private String userKorName;  // 이름

    @Column(nullable = false)
    private String userNickname;  // 닉네임

    @Column(nullable = false)
    private String userPhone;  // 전화번호

    private String userProfileImg;  // 프로필 이미지 경로

    @Column(nullable = false)
    private LocalDateTime userBirth;  // 생년월일

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime userRegister;  // 가입일

    @Column(nullable = false)
    private int userTos;  // 서비스 이용약관 동의

    @Column(nullable = false)
    private int userPrivacy;  // 개인정보 동의

    @Column(nullable = false)
    private int userMarketing;  // 마케팅 동의

    @ColumnDefault("0")
    private Authority authority;  // 관리자 권한 여부

    // 생성자
    @Builder
    public User(int userId, String userEmail, String userPassword, String userKorName, String userNickname,
                String userPhone, String userProfileImg, LocalDateTime userBirth, LocalDateTime userRegister,
                int userTos, int userPrivacy, int userMarketing, Authority authority){
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userKorName = userKorName;
        this.userNickname = userNickname;  // 중복값 있는 지는 프론트에서 확인
        this.userPhone = userPhone;
        this.userProfileImg = userProfileImg;
        this.userBirth = userBirth;
        this.userRegister = userRegister == null ? LocalDateTime.now() : userRegister;
        this.userTos = userTos;
        this.userPrivacy = userPrivacy;
        this.userMarketing = userMarketing;
        this.authority = authority;
    }

    // 수정을 위한 Setter
    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }
    public void setUserNickname(String userNickname){
        this.userNickname = userNickname;
    }
    public void setUserPhone(String userPhone){
        this.userPhone = userPhone;
    }
    public void setUserProfileImg(String userProfileImg){
        this.userProfileImg = userProfileImg;
    }

    // For UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(("user")));
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
