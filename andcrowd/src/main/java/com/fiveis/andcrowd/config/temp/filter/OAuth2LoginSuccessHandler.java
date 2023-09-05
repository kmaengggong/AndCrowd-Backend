//package com.fiveis.andcrowd.config.oauth;
//
//import com.fiveis.andcrowd.repository.user.UserJPARepository;
//import com.fiveis.andcrowd.service.user.JwtService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
//    private final JwtService jwtService;
//    private final UserJPARepository userJPARepository;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication)
//        throws ServletException, IOException {
//        try{
//            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
//            loginSuccess(response, oAuth2User);
//        } catch (Exception e){
//            throw  e;
//        }
//    }
//
//    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User)
//        throws IOException{
//        String accessToken = jwtService.generateToken(oAuth2User.getUserEmail(), JwtService.ACCESS_TOKEN_DURATION);
//        String refreshToken = jwtService.generateToken(oAuth2User.getUserEmail(), JwtService.REFRESH_TOKEN_DURATION);
//
//        response.addHeader(JwtService.ACCESS_TOKEN_HEADER, "Bearer " + accessToken);
//        response.addHeader(JwtService.REFRESH_TOKEN_HEADER, "Bearer " + refreshToken);
//
//        jwtService.sendAccessTokenAndRefreshToken(response, accessToken, refreshToken);
//        jwtService.updateRefreshToken(oAuth2User.getUserEmail(), refreshToken);
//    }
//}
