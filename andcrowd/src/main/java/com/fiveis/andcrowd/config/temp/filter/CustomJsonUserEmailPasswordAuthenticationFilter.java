//package com.fiveis.andcrowd.config.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.util.StreamUtils;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
//public class CustomJsonUserEmailPasswordAuthenticationFilter
//        extends AbstractAuthenticationProcessingFilter {
//    private final static AntPathRequestMatcher DEFAULT_LOGIN_PATH_REQUEST_MATCHER
//            = new AntPathRequestMatcher("/login", "POST");
//    private final ObjectMapper objectMapper;
//
//    public CustomJsonUserEmailPasswordAuthenticationFilter(ObjectMapper objectMapper){
//        super(DEFAULT_LOGIN_PATH_REQUEST_MATCHER);
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response
//                                                )
//            throws AuthenticationException, IOException{
//        if(request.getContentType() == null || !(
//                request.getContentType().equals("application/json; charset=utf-8") ||
//                request.getContentType().equals("application.json;"))) {
//            System.out.println(request.getContentType());
//            throw new AuthenticationServiceException("Authentication Content-Type not supported: " + request.getContentType());
//        }
//
//
//        String messageBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
//        Map<String, String> usernamePasswordMap = objectMapper.readValue(messageBody, Map.class);
//        String userEmail = usernamePasswordMap.get("userEmail");
//        String userPassword = usernamePasswordMap.get("userPassword");
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userEmail, userPassword);
//
//        return this.getAuthenticationManager().authenticate(authenticationToken);
//    }
//}
