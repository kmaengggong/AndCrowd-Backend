package com.fiveis.andcrowd.controller.user;

import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.dto.etc.AccessTokenResponseDTO;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserController(UserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          TokenProvider tokenProvider){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String logIn(){
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        System.out.println("!!!!! " + user);
        User userInfo = userService.getByCredentials(user.getUserEmail());

        if(bCryptPasswordEncoder.matches(user.getPassword(), userInfo.getUserPassword())){
            String token = tokenProvider.generateToken(userInfo, Duration.ofHours(2));
            AccessTokenResponseDTO tokenResponseDTO = new AccessTokenResponseDTO(token);
            return ResponseEntity.ok(tokenResponseDTO);
        }
        else{
            return ResponseEntity.badRequest().body("Log In Failed");
        }
    }

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signupForm(){
        return "signup";
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signup(@RequestBody User user){
        userService.save(user);
        return "redirect:/login";
    }
}