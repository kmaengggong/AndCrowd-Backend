package com.fiveis.andcrowd.config.oauth;

import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.entity.etc.RefreshToken;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.repository.etc.RefreshTokenRepository;
import com.fiveis.andcrowd.service.user.UserService;
import com.fiveis.andcrowd.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final String REDIRECT_PATH = "/";

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // @Configuration 으로 등록된 설정 클래스 내부 메서드에 @Bean 어노테이션이 붙어있다면
    // 해당 메서드가 리턴하는 빈도 빈 컨테이너에 자동으로 등록됩니다
    // 아래 클래스 인스턴스의 경우는 BasicSecurityConfig 클래스 내부 메서드에 의해 생성된 빈을 주입받습니다.
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final UserService userService; // 임포트 해야 자동주입

//    @Autowired
//    public OAuth2SuccessHandler(TokenProvider tokenProvider,
//                                RefreshTokenRepository refreshTokenRepository,
//                                OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository,
//                                UserService userService){
//        this.tokenProvider = tokenProvider;
//        this.refreshTokenRepository = refreshTokenRepository;
//        this.authorizationRequestRepository = authorizationRequestRepository;
//        this.userService = userService;
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        System.out.println("onAuthenticationSuccess");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal(); // 인증 정보 가져옴
        User user = userService.findByUserEmail((String) oAuth2User.getAttributes().get("userEmail")); // 인증 객체 내부 이메일로 유저정보 조회

        // 인증된 유저가 맞다면 리프레시 토큰을 생성해서 쿠키에 저장
        // 리프레시 토큰도 JWT토큰의 일종이므로 TokenProvider로 생성
        String refreshToken = tokenProvider.generateToken(user, TokenProvider.REFRESH_TOKEN_DURATION);
        // 함수를 호출해서 뭔가 로직을 처리하는 부분은 대부분 리팩토링을 위해 외부 함수에 실행로직을 빼 둔 경우입니다.
        saveRefreshToken(user.getUserId(), refreshToken); // 토큰을 디비에 저장
        addRefreshTokenToCookie(request, response, refreshToken); // 사용자측에 보내기 위한 쿠키 적재

        // 전달된 리프레시토큰을 이용해 억세스토큰 생성 및 활용하기
        String accessToken = tokenProvider.generateToken(user, TokenProvider.ACCESS_TOKEN_DURATION);
        String targetUrl = getTargetUrl(accessToken);

        // 인증 관련 설정값, 쿠키 제거
        clearAuthenticationAttributes(request, response);

        // 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    // 상단에서 호출중인 함수를 작성하는 부분
    // 생성된 리프레시토큰을 전달받아 DB에 적재하는 부분
    private void saveRefreshToken(int userId, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId);

        // 위에서 얻어온 토큰정보가 있다면 새 토큰으로 갱신, 없다면 신규발급
        if(refreshToken != null){
            refreshToken.update(newRefreshToken);
        } else {
            refreshToken = new RefreshToken(userId, newRefreshToken);
        }

        refreshTokenRepository.save(refreshToken);
    }

    // 생성된 리프레시 토큰을 쿠키에 저장하는 함수
    private void addRefreshTokenToCookie(HttpServletRequest request,
                                         HttpServletResponse response,
                                         String refreshToken) {
        int cookieMaxAge = (int) TokenProvider.REFRESH_TOKEN_DURATION.toSeconds();// 초단위로 바꿔서 쿠키 유지 시간 저장
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    // 억세스토큰을 소셜로그인 경로에 파라미터로 추가
    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token", token)
                .build()
                .toUriString();
    }

    // 전달 완료 후 인증 관련 설정값, 쿠키 제거
    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response){
        // 상속 전 부모클래스쪽에 정의된 메서드는 request에 대해서만 소거가 처리되므로, 쿠키 제거를 위해 response 부분도 추가 처리
        // 메서드명칭은 부모쪽 메서드와 같지만 받는 파라미터의 종류가 다르므로 오버로딩
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

}
