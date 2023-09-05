package com.fiveis.andcrowd.controller.etc;

import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.dto.etc.AccessTokenResponseDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.service.etc.TokenService;
import com.fiveis.andcrowd.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TokenController {
    private final TokenService tokenService;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    @RequestMapping(value="/getUserId", method=RequestMethod.POST)
    public ResponseEntity<?> getUserId(HttpServletRequest request){
        try{
            return ResponseEntity.ok(tokenProvider.getUserId(request.getHeader("Authorization")));
        } catch(Exception e){
            return ResponseEntity.badRequest().body("/getUserId: Invalid Access Token");
        }

    }

    @RequestMapping(value="/accessTokenValid", method=RequestMethod.POST)
    public ResponseEntity<?> isAccessTokenValid(@RequestBody AccessTokenResponseDTO accessToken){
        if(tokenProvider.validToken(accessToken.getAccessToken())){
            return ResponseEntity.ok("Valid AccessToken");
        }
        else {
            return ResponseEntity.badRequest().body("Invalid AccessToken");
        }
    }

    @RequestMapping(value="/getNewAccessToken", method=RequestMethod.POST)
    public ResponseEntity<?> createNewAccessToken(
            HttpServletRequest request,
            HttpServletResponse response,
            @CookieValue String refresh_token){
        if(tokenProvider.isRefreshTokenValid(refresh_token)){
            int userId = tokenProvider.getUserId(refresh_token);
            UserDTO.FindAsAdmin findAsAdmin = userService.findById(userId);
            User user = userService.getByCredentials(findAsAdmin.getUserEmail());

            // 리프레쉬 토큰 생성
            String accessToken = tokenService.createAndSaveRTAndGetAT(request, response, user);
            AccessTokenResponseDTO accessTokenResponseDTO = new AccessTokenResponseDTO(accessToken);

            // 엑세스 토큰은 로컬에 저장하기 때문에 반환
            return ResponseEntity.ok(accessTokenResponseDTO);
        }
        else{
            tokenService.deleteRefreshToken(request, response);
            return ResponseEntity.badRequest().body("/getNewAccessToken: Invalid RefreshToken");
        }
    }
}
