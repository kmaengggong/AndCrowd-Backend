//package com.fiveis.andcrowd.service.user;
//
//import com.fiveis.andcrowd.config.jwt.JwtProperties;
//import com.fiveis.andcrowd.entity.user.User;
//import com.fiveis.andcrowd.repository.user.UserJPARepository;
//import io.jsonwebtoken.Header;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.Date;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Getter
//public class JwtService {
//    public final static Duration ACCESS_TOKEN_DURATION = Duration.ofHours(2);
//    public final static Duration REFRESH_TOKEN_DURATION = Duration.ofDays(2);
//    public final static String ACCESS_TOKEN_HEADER = "Authorization";
//    public final static String REFRESH_TOKEN_HEADER = "Authorization-refresh";
//    private final static String TOKEN_PREFIX = "Bearer ";
//
//    private final JwtProperties jwtProperties;
//    private final UserJPARepository userJPARepository;
//
//    public String generateToken(String userEmail, Duration expiredAt){
//        Date now = new Date();
//        User user = userJPARepository.findByUserEmail(userEmail).get();
//        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
//    }
//
//    private String makeToken(Date expiry, User user){
//        Date now = new Date();
//
//        return Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
//                .setIssuer(jwtProperties.getIssuer())
//                .setIssuedAt(now)
//                .setExpiration(expiry)
//                .setSubject(user.getUserEmail()) // 기본적으로는 아이디를 줍니다
//                .claim("userId", user.getUserId()) // 클레임에는 PK제공
//                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
//                .compact();
//    }
//
//    public void sendAccessToken(HttpServletResponse response, String accessToken){
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.setHeader(ACCESS_TOKEN_HEADER, accessToken);
//    }
//
//    public void sendAccessTokenAndRefreshToken(HttpServletResponse response,
//                                               String accessToken,
//                                               String refreshToken){
//        response.setStatus(HttpServletResponse.SC_OK);
//        setAccessTokenHeader(response, accessToken);
//        setRefreshTokenHeader(response, refreshToken);
//    }
//
//    public Optional<String> extractRefreshToken(HttpServletRequest request){
//        return Optional
//                .ofNullable(request.getHeader(REFRESH_TOKEN_HEADER))
//                .filter(refreshToken -> refreshToken.startsWith(TOKEN_PREFIX))
//                .map(refreshToken -> refreshToken.replace(TOKEN_PREFIX, ""));
//    }
//
//    public Optional<String> extractAccessToken(HttpServletRequest request){
//        return Optional
//                .ofNullable(request.getHeader(ACCESS_TOKEN_HEADER))
//                .filter(refreshToken -> refreshToken.startsWith(TOKEN_PREFIX))
//                .map(refreshToken -> refreshToken.replace(TOKEN_PREFIX, ""));
//    }
//
//    public Optional<String> extractEmail(String accessToken){
//        try{
//            return Optional
//                    .ofNullable(Jwts.parser()
//                            .setSigningKey(jwtProperties.getSecretKey())
//                            .parseClaimsJws(accessToken)
//                            .getBody()
//                            .getSubject()
//                    );
//
//        } catch(Exception e){
//            return Optional.empty();
//        }
//    }
//
//    public void setAccessTokenHeader(HttpServletResponse response, String accessToken){
//        response.setHeader(ACCESS_TOKEN_HEADER, accessToken);
//    }
//
//    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken){
//        response.setHeader(REFRESH_TOKEN_HEADER, refreshToken);
//    }
//
//    public void updateRefreshToken(String userEmail, String refreshToken){
//        userJPARepository.findByUserEmail(userEmail)
//                .ifPresentOrElse(
//                       user -> user.updateRefreshToken(refreshToken),
//                        () -> new Exception("일치하는 유저가 없습니다.")
//                );
//    }
//
//    public boolean isTokenValid(String token){
//        try{
//            Jwts.parser()
//                    .setSigningKey(jwtProperties.getSecretKey())
//                    .parseClaimsJws(token);
//            return true;
//        } catch (Exception e){
//            return false;
//        }
//    }
//}