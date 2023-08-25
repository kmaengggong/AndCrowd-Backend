package com.fiveis.andcrowd.controller.user;

import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.dto.etc.AccessTokenResponseDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.service.user.MailService;
import com.fiveis.andcrowd.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;

@RestController
public class SignController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final MailService mailService;

    @Autowired
    public SignController(UserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          TokenProvider tokenProvider,
                          MailService mailService){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenProvider = tokenProvider;
        this.mailService = mailService;
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody UserDTO.Login userLogin) {
        System.out.println("!!!!! " + userLogin);
        User userInfo = userService.getByCredentials(userLogin.getUserEmail());
        if(userInfo == null) return ResponseEntity.badRequest().body("Log In Failed");

        if(bCryptPasswordEncoder.matches(userLogin.getUserPassword(), userInfo.getUserPassword())){
            String token = tokenProvider.generateToken(userInfo, Duration.ofHours(2));
            AccessTokenResponseDTO tokenResponseDTO = new AccessTokenResponseDTO(token);
            return ResponseEntity.ok(tokenResponseDTO);
        }
        else{
            return ResponseEntity.badRequest().body("Log In Failed");
        }
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody User user){
        try{
            userService.save(user);
            return ResponseEntity.ok("Sign Up Success!");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Sign Up Failed");
        }
    }

    @RequestMapping(value="/mailAuth", method=RequestMethod.POST)
    public ResponseEntity<?> getEmailAuthNumber(@RequestBody Map<String, String> userEmail){
        System.out.println(userEmail.get("userEmail"));
        String res = mailService.setEmail(userEmail.get("userEmail"));
        System.out.println(res);
        if(res == null) return ResponseEntity.badRequest().body("Sth wrong dude");
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value="/nicknameCheck", method=RequestMethod.POST)
    public ResponseEntity<?> checkNickname(@RequestBody Map<String, String> userNickname){
        System.out.println(userNickname.get("userNickname"));
        if(userService.findByUserNickname(userNickname.get("userNickname")) != null)
            return ResponseEntity.badRequest().body("Nickname already exist");
        else return ResponseEntity.ok("Good to use");
    }
}
