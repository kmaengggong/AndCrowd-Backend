package com.fiveis.andcrowd.config.oauth;

import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.repository.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    // 소셜로그인 관련 서비스 레이어도 어차피 회원기능 관련이므로 유저레포지토리쪽에 관여합니다.
    private final UserJPARepository userJPARepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println(("loadUser"));
        // 소셜로그인 요청이 들어오면 해당 정보를 클라이언트측에서 사용하는 User 객체로 변환
        OAuth2User user = super.loadUser(userRequest);
        // 로그인 유저가 없으면 신규가입, 있으면 정보를 갱신만
        saveOrUpdate(user);
        return user;
    }

    // 위에서 호출하는 saveOrUpdate 정의
    // 유저가 있으면 업데이트, 없으면 인서트
    private User saveOrUpdate(OAuth2User oAuth2User) {
        System.out.println("saveOrUpdate");
        // 유저 내용을 키,벨류 쌍으로 Map형식으로 변환(변수명 : key, 변수에 대입된 값 : value)
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email"); // email 변수에 담겨있단 이메일 값 받아오기

        // DB에서 유저를 가지고 와 봐서
        User user = userJPARepository.findByUserEmail(email).get();

        if(user == null) { // 없는 유저면 가입용으로 새로 객체 생성
            user = User.builder()
                    .userEmail(email)
                    .build();
        } else {
            user.update(email);// 있는 유저면 name만 수정
        }

        // 있으면 수정, 없으면 삽입
        return userJPARepository.save(user);
    }

}