package com.fiveis.andcrowd.config;

import com.fiveis.andcrowd.config.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    // 토큰제공자 내부의 validToken메서드를 이용해서 토큰의 유효성을 검증할 예정이므로 의존성 주입
    private final TokenProvider tokenProvider;

    // 토큰은 RequestHeader에 포함되서 들어오므로, 해당 요소를 얻어낼 수 있도록 명칭 상수화
    private final static String HEADER_AUTHORIZATION = "Authorization";
    // 토큰기반 인증시 실제 토큰값 앞에 접두어로 붙는 Bearer 제거를 위한 상수
    private final static String TOKEN_PREFIX = "Bearer ";

    // 아래 doFilterInternal은 매 요청마다 실행됩니다.
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 토큰은 요청헤더를 이용해서 제출되기 때문에 , request에서 header를 얻어와야 합니다.
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        // 얻어온 토큰에서 Bearer 접두사를 삭제하고 토큰값만 얻어내기
        String token = getAccessToken(authorizationHeader); // 토큰값 발라내는 로직은 아래에 메서드로 분리

        if (tokenProvider.validToken(token)) { // 얻어온 토큰이 유효 토큰이라면
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 완료해주기
        }

        filterChain.doFilter(request, response);
    }

    // 들어온 토큰에서 Bearer 접두사 제거로직
    private String getAccessToken(String authorizationHeader) {
        // 우선 토큰은 발급된 상태이며          Bearer 로 시작하는 상태라면
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());// 앞 7글자(Bearer )를 소거한 값만 리턴
        }
        return null;
    }
}
