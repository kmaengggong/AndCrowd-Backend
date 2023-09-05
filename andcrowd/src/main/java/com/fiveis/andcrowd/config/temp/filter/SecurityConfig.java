//package com.fiveis.andcrowd.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fiveis.andcrowd.config.filter.CustomJsonUserEmailPasswordAuthenticationFilter;
//import com.fiveis.andcrowd.config.filter.JwtAuthenticationFilter;
//import com.fiveis.andcrowd.config.oauth.CustomOAuth2UserService;
//import com.fiveis.andcrowd.config.oauth.OAuth2LoginFailureHandler;
//import com.fiveis.andcrowd.config.oauth.OAuth2LoginSuccessHandler;
//import com.fiveis.andcrowd.repository.user.UserJPARepository;
//import com.fiveis.andcrowd.service.user.JwtService;
//import com.fiveis.andcrowd.service.user.UserAuthorityService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.logout.LogoutFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    private final UserAuthorityService userAuthorityService;
//    private final JwtService jwtService;
//    private final UserJPARepository userJPARepository;
//    private final ObjectMapper objectMapper;
//    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
//    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http
//                .formLogin(formLoginConfig -> {
//                    formLoginConfig
//                            .disable();
//                })
//                .httpBasic(httpBasicConfig -> {
//                    httpBasicConfig
//                            .disable();
//                })
//                .csrf(csrfConfig -> {
//                    csrfConfig
//                            .disable();
//                })
////                .headers(AbstractHttpConfigurer::disable)
//                .sessionManagement(sessionConfig -> {
//                    sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                })
//                .authorizeHttpRequests(authorizationConfig -> {
//                    authorizationConfig
//                            .requestMatchers("/", "/login", "/signup").permitAll()
//                            .anyRequest().authenticated();
//                })
//                .oauth2Login(oauth2config -> {
//                    oauth2config
//                            .successHandler(oAuth2LoginSuccessHandler)
//                            .failureHandler(oAuth2LoginFailureHandler);
////                            .userInfoEndpoint()
////                            .userService(customOAuth2UserService);
//                })
//                .logout(logoutConfig -> {
//                    logoutConfig
//                            .logoutUrl("/logout") // 디폴트로 "/logout"으로 잡아주기 때문에 굳이 설정할필요없음
//                            .logoutSuccessUrl("/login")
//                            .invalidateHttpSession(true);
//                })
//                .addFilterAfter(customJsonUserEmailPasswordAuthenticationFilter(), LogoutFilter.class)
//                .addFilterBefore(jwtAuthenticationFilter(), CustomJsonUserEmailPasswordAuthenticationFilter.class)
//                .build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(userAuthorityService);
//        return new ProviderManager(provider);
//    }
//
//    @Bean
//    public LoginSuccessHandler loginSuccessHandler(){
//        return new LoginSuccessHandler(jwtService, userJPARepository);
//    }
//
//    @Bean
//    public LoginFailureHandler loginFailureHandler(){
//        return new LoginFailureHandler();
//    }
//
//    @Bean
//    public CustomJsonUserEmailPasswordAuthenticationFilter customJsonUserEmailPasswordAuthenticationFilter(){
//        CustomJsonUserEmailPasswordAuthenticationFilter customJsonUserEmailPasswordAuthenticationFilter
//                = new CustomJsonUserEmailPasswordAuthenticationFilter(objectMapper);
//
//        customJsonUserEmailPasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        customJsonUserEmailPasswordAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
//        customJsonUserEmailPasswordAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler());
//        return customJsonUserEmailPasswordAuthenticationFilter;
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
//                jwtService, userJPARepository);
//        return jwtAuthenticationFilter;
//    }
//}
