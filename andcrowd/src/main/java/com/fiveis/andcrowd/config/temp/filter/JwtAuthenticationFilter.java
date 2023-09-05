//package com.fiveis.andcrowd.config.filter;
//
//import com.fiveis.andcrowd.entity.user.User;
//import com.fiveis.andcrowd.repository.user.UserJPARepository;
//import com.fiveis.andcrowd.service.user.JwtService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
//import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Random;
//
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter{
//    private final static String NO_CHECK_URL = "/login";
//    private final JwtService jwtService;
//    private final UserJPARepository userJPARepository;
//    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//        throws ServletException, IOException{
//        if(request.getRequestURI().equals(NO_CHECK_URL)){
//            filterChain.doFilter(request, response);
//            System.out.println("/login 접근");
//            return;
//        }
//
//        String refreshToken = jwtService.extractRefreshToken(request)
//                .filter(jwtService::isTokenValid)
//                .orElse(null);
//
//        if(refreshToken != null){
//            System.out.println("리프레쉬 토큰 있음");
//            checkRefreshTokenAndReIssuAccessToken(response, refreshToken);
//            return;
//        }
//
//        if(refreshToken == null){
//            System.out.println("리프레쉬 토큰 없음");
//            checkAccessTokenAndAuthentication(request, response, filterChain);
//        }
//    }
//
//    public void checkRefreshTokenAndReIssuAccessToken(HttpServletResponse response,
//                                                      String refreshToken){
//        userJPARepository.findByRefreshToken(refreshToken)
//                .ifPresent(user -> {
//                    String reIssuedRefreshToken = reIssueRefreshToken(user);
//                    jwtService.sendAccessTokenAndRefreshToken(response,
//                            jwtService.generateToken(user.getUserEmail(), Duration.ofHours(2)),
//                                    reIssuedRefreshToken);
//                });
//    }
//
//    private String reIssueRefreshToken(User user){
//        String reIssuedRefreshToken = jwtService.generateToken(user.getUserEmail(),
//                Duration.ofDays(2));
//        user.updateRefreshToken(reIssuedRefreshToken);
//        userJPARepository.saveAndFlush(user);
//        return reIssuedRefreshToken;
//    }
//
//    public void checkAccessTokenAndAuthentication(HttpServletRequest request,
//                                                  HttpServletResponse response,
//                                                  FilterChain filterChain)
//        throws ServletException, IOException{
//        System.out.println("checkAccessTokenAndAuthentication 호출");
//        jwtService.extractAccessToken(request)
//                .filter(jwtService::isTokenValid)
//                .ifPresent(accessToken -> jwtService.extractEmail(accessToken)
//                        .ifPresent(userEmail -> userJPARepository.findByUserEmail(userEmail)
//                                .ifPresent(this::saveAuthentication)));
//        filterChain.doFilter(request, response);
//    }
//
//    public void saveAuthentication(User user){
//        System.out.println("saveAuthentication 호출");
//        String userPassword = user.getUserPassword();
//        if(userPassword == null){
//            userPassword = generateRandomPassword();
//        }
//
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUserEmail())
//                .password(userPassword)
//                .roles(user.getRole().name())
//                .build();
//
//        Authentication authentication =
//                new UsernamePasswordAuthenticationToken(userDetails,
//                        null,
//                        authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//
//    public String generateRandomPassword(){
//        int index = 0;
//        char[] charSet = new char[]{
//                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
//                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
//                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
//                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
//                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
//                'y', 'z'
//        };
//
//        StringBuffer password = new StringBuffer();
//        Random random = new Random();
//
//        for(int i=0; i<8; i++){
//            double rd = random.nextDouble();
//            index = (int)(charSet.length * rd);
//            password.append(charSet[index]);
//        }
//
//        return password.toString();
//    }
//}
