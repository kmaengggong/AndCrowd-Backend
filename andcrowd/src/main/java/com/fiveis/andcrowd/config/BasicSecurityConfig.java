package com.fiveis.andcrowd.config;

import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.service.UserAuthorityService;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class BasicSecurityConfig {
    // 등록할 시큐리티 서비스 멤버변수로 작성하기
    private final UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider; // final 을 붙이는 이유는 해당 객체들을 변경할 이유가 없기때문에 불변성 보장

    @Autowired
    public BasicSecurityConfig(UserDetailsService userDetailsService,
                               TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    // 정적 파일이나 .jsp 파일 등 스프링 시큐리티가 기본적으로 적용되지 않을 영역 설정하기.
    @Bean // @Configuration 어노테이션 붙은 클래스 내부 메서드가 리턴하는 자료는 자동으로 빈에 등록됩니다.
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring() // 시큐리티 적용을 안 할 경로
                .requestMatchers("/static/**")
                // 기본 경로는 src/main/java/resources 로 잡히고
                // 추후 설정할 정적자원 저장 경로에 보안을 풀었음.
                .dispatcherTypeMatchers(DispatcherType.FORWARD);
        // MVC방식에서 뷰단 파일을 로딩하는것을 보안범위에서 해제.
        // 이 설정을 하지 않으면, .jsp파일이 화면에 출력되지 않습니다.

    }

    // http 요청에 대한 웹 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizationConfig -> {
                    authorizationConfig
                            .requestMatchers("/login", "/signup", "/user")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(formLoginConfig -> {
                    formLoginConfig
                            //.loginPage("/login") // 폼에서 날려준 정보를 토대로 로그인 처리를 해주는 주소(post)
                            //.defaultSuccessUrl("/blog/list");
                            .disable(); // 토큰기반 로그인시에는 폼 로그인을 막아야 합니다.
                })
                .logout(logoutConfig -> {
                    logoutConfig
                            .logoutUrl("/logout") // 디폴트로 "/logout"으로 잡아주기 때문에 굳이 설정할필요없음
                            .logoutSuccessUrl("/login")
                            .invalidateHttpSession(true);
                })
                .sessionManagement(sessionConfig -> {
                    sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .csrf(csrfConfig -> {
                    csrfConfig
                            .disable();
                })
                // Before시점(Request를 서버가 처리하기 직전 시점)에 해당 필터를 사용해 로그인을 검증하도록 설정
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();

                /*
                .authorizeRequests() // 인증, 인가 설정 시작부에 사용하는 메서드
                .requestMatchers("/login", "/signup", "/user")
                .permitAll() // 위 경로들은 인증 없이 접속 가능
                .anyRequest() // 위에 적힌 경로 말고는
                .authenticated() // 로그인 필수임.
                .and() // 다음 설정으로 넘어가기
                .formLogin() // 로그인 폼으로 로그인 제어
                .loginPage("/login") // 로그인 페이지로 지정할 주소
                .defaultSuccessUrl("/blog/list") // 로그인 하면 처음으로 보여질 페이지
                .and()
                .logout() // 로그아웃 관련 설정
                .logoutSuccessUrl("/login") // 로그아웃 성공했으면 넘어갈 경로
                .invalidateHttpSession(true) // 로그아웃하면 다음 접속시 로그인이 풀려있게 설정
                .and()
                .csrf() // csrf 공격 방지용 토큰
                .disable() // 을 쓰지 않겠음.
                .build();
                 */
    }

    // 위의 설정을 따라가는 인증은 어떤 서비스 클래스를 통해서 설정할것인가?
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserAuthorityService userAuthorityService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userAuthorityService) // userService에 기술된 내용을 토대로 로그인처리
                .passwordEncoder(bCryptPasswordEncoder) // 비밀번호 암호화 저장 모듈
                .and()
                .build();

    }

    // 암호화 모듈 임포트
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 필터 클래스 생성(필터 클래스도 빈 컨테이너에 적재되어 있어야 사용 가능하므로)
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);// 필터는 생성자에서 토큰제공자(TokenProvider 클래스)를 요구합니다.
    }
}
