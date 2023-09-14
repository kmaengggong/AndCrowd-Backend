package com.fiveis.andcrowd.controller.etc;

import com.fiveis.andcrowd.config.NaverOauthConfig;
import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.dto.etc.AccessTokenResponseDTO;
import com.fiveis.andcrowd.dto.user.NaverDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.service.etc.TokenService;
import com.fiveis.andcrowd.service.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TokenController {
    private final TokenService tokenService;
    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final NaverOauthConfig naverOauthConfig;

//    @RequestMapping(value="/getUserId", method=RequestMethod.POST)
//    public ResponseEntity<?> getUserId(HttpServletRequest request){
//        try{
//            return ResponseEntity.ok(tokenProvider.getUserId(request.getHeader("Authorization")));
//        } catch(Exception e){
//            return ResponseEntity.badRequest().body("/getUserId: Invalid Access Token");
//        }
//    }

//    @RequestMapping(value="/accessTokenValid", method=RequestMethod.POST)
//    public ResponseEntity<?> isAccessTokenValid(@RequestBody AccessTokenResponseDTO accessToken){
//        if(tokenProvider.validToken(accessToken.getAccessToken())){
//            return ResponseEntity.ok("Valid AccessToken");
//        }
//        else {
//            return ResponseEntity.badRequest().body("Invalid AccessToken");
//        }
//    }

    @RequestMapping(value="/accessTokenValid", method=RequestMethod.POST)
    public ResponseEntity<?> isAccessTokenValid(HttpServletRequest request,
                                                HttpServletResponse response){
        try{
            // 권한 확인
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            List<String> authorityList = authentication
                    .getAuthorities()
                    .stream()
                    .map(authority -> authority.toString())
                    .toList();

            // 관리자 유저의 경우
            if(authorityList.contains("ROLE_ADMIN")){
                System.out.println("!!! 어드민 유저");
            }
            // 로그인 한 경우
            else if(authorityList.contains("ROLE_USER")){
                System.out.println("!!! 로그인한 유저");
            }
            // 로그인 안한 경우
            else{
                System.out.println("!!! 비로그인 유저 혹은 AccessToken 만료");
            }

            // 쿠키를 통해 리프레쉬 토큰 추출
            Cookie[] cookies = request.getCookies();
            String refreshToken = "";
            if(cookies == null) return ResponseEntity.badRequest().body("NoRefreshToken");
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("refresh_token")){
                    refreshToken = cookie.getValue();
                    break;
                }
            }

            if(!tokenProvider.validToken(refreshToken)) return ResponseEntity.badRequest().body("InvalidRefreshToken");

            int userId = tokenProvider.getUserId(refreshToken);
            UserDTO.FindAsAdmin findAsAdmin = userService.findById(userId);
            User user = userService.getByCredentials(findAsAdmin.getUserEmail());

            // 새 리프레쉬 토큰 생성
            String accessToken = tokenService.createAndSaveRTAndGetAT(request, response, user);
            AccessTokenResponseDTO accessTokenResponseDTO = new AccessTokenResponseDTO(accessToken);

            // 엑세스 토큰은 로컬에 저장하기 때문에 반환
            return ResponseEntity.ok(accessTokenResponseDTO);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("/accessTokenValid: " + e);
        }
    }

//    @RequestMapping(value="/getNewAccessToken", method=RequestMethod.POST)
//    public ResponseEntity<?> createNewAccessToken(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            @CookieValue String refresh_token){
//        if(tokenProvider.isRefreshTokenValid(refresh_token)){
//            int userId = tokenProvider.getUserId(refresh_token);
//            UserDTO.FindAsAdmin findAsAdmin = userService.findById(userId);
//            User user = userService.getByCredentials(findAsAdmin.getUserEmail());
//
//            // 리프레쉬 토큰 생성
//            String accessToken = tokenService.createAndSaveRTAndGetAT(request, response, user);
//            AccessTokenResponseDTO accessTokenResponseDTO = new AccessTokenResponseDTO(accessToken);
//
//            // 엑세스 토큰은 로컬에 저장하기 때문에 반환
//            return ResponseEntity.ok(accessTokenResponseDTO);
//        }
//        else{
//            tokenService.deleteRefreshToken(request, response);
//            return ResponseEntity.badRequest().body("/getNewAccessToken: Invalid RefreshToken");
//        }
//    }

    @RequestMapping(value="/oauth/naver", method=RequestMethod.GET)
    public ResponseEntity<?> naverLogin(HttpServletResponse response/*@RequestBody NaverDTO.Params params*/) throws IOException {
        System.out.println("naverLogin");

        String uri = UriComponentsBuilder
                .fromUriString("https://nid.naver.com/oauth2.0/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", naverOauthConfig.getClientId())
                .queryParam("state", "test")
                .queryParam("redirect_uri", naverOauthConfig.getRedirectUri())
                .toUriString();

        System.out.println(uri);
        response.sendRedirect(uri);

        return ResponseEntity.ok().build();
    }

}
