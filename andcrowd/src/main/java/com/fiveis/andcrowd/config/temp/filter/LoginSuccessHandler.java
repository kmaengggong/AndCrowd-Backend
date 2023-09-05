//package com.fiveis.andcrowd.config;
//
//import com.fiveis.andcrowd.repository.user.UserJPARepository;
//import com.fiveis.andcrowd.service.user.JwtService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//
//import java.time.Duration;
//
//@RequiredArgsConstructor
//public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    private final JwtService jwtService;
//    private final UserJPARepository userJPARepository;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication){
//        String userEmail = extractUsername(authentication);
//        String accessToken = jwtService.generateToken(userEmail, JwtService.ACCESS_TOKEN_DURATION);
//        String refreshToken = jwtService.generateToken(userEmail, JwtService.REFRESH_TOKEN_DURATION);
//
//        jwtService.sendAccessTokenAndRefreshToken(response, accessToken, refreshToken);
//
//        userJPARepository.findByUserEmail(userEmail)
//                .ifPresent(user -> {
//                    user.updateRefreshToken(refreshToken);
//                    userJPARepository.saveAndFlush(user);
//                });
//    }
//
//    private String extractUsername(Authentication authentication){
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        return userDetails.getUsername();
//    }
//}
