//package com.fiveis.andcrowd.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//
//import java.io.IOException;
//
//public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        AuthenticationException exception)
//        throws IOException{
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/plain;charset=UTF-8");
//        response.getWriter().write("이메일 혹은 비밀번호를 확인해주세요.");
//    }
//}
