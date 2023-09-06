package com.fiveis.andcrowd.controller.user;

//import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.dto.etc.AccessTokenResponseDTO;
import com.fiveis.andcrowd.dto.etc.RefreshTokenRequestDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.service.etc.TokenService;
import com.fiveis.andcrowd.service.user.MailService;
import com.fiveis.andcrowd.service.user.UserService;
import com.fiveis.andcrowd.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SignController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;
    private final MailService mailService;

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseEntity<?> authenticate(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestBody UserDTO.Login userLogin) {
        System.out.println("/login: " + userLogin);
        User userInfo = userService.getByCredentials(userLogin.getUserEmail());
        if(userInfo == null) return ResponseEntity.badRequest().body("Log In Failed");

        // 로그인 성공 시 리프레쉬 토큰, 엑세스 토큰 발급
        if(bCryptPasswordEncoder.matches(userLogin.getUserPassword(), userInfo.getUserPassword())){

            // 리프레쉬 토큰 생성
            String accessToken = tokenService.createAndSaveRTAndGetAT(request, response, userInfo);
            AccessTokenResponseDTO accessTokenResponseDTO = new AccessTokenResponseDTO(accessToken);

            // 엑세스 토큰은 로컬에 저장하기 때문에 반환
            return ResponseEntity.ok(accessTokenResponseDTO);
        }
        else{
            return ResponseEntity.badRequest().body("Log In Failed");
        }
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody User user){
        System.out.println("/signup: " + user);
        try{
            userService.save(user);
            return ResponseEntity.ok("Sign Up Success!");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Sign Up Failed");
        }
    }

    @RequestMapping(value="/mailAuth", method=RequestMethod.POST)
    public ResponseEntity<?> getEmailAuthNumber(@RequestBody Map<String, String> userEmail){
        System.out.println("/mailAuth: " + userEmail.get("userEmail"));
        if(userService.findByUserEmail(userEmail.get("userEmail")) != null){
            return ResponseEntity.badRequest().body("이미 존재하는 이메일입니다.");
        }
        String res = mailService.setEmail(userEmail.get("userEmail"));
        System.out.println(res);
        if(res == null) return ResponseEntity.internalServerError().body("서버 오류입니다.");
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value="/nicknameCheck", method=RequestMethod.POST)
    public ResponseEntity<?> checkNickname(@RequestBody Map<String, String> userNickname){
        System.out.println("/nicknameCheck: " + userNickname.get("userNickname"));
        if(userService.findByUserNickname(userNickname.get("userNickname")) != null)
            return ResponseEntity.badRequest().body("Nickname already exist");
        else return ResponseEntity.ok("Good to use");
    }
}
