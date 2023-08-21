package com.fiveis.andcrowd.controller.user;

import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.dto.etc.AccessTokenResponseDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/login", method=RequestMethod.POST)
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

    @RequestMapping(value="/user/list", method=RequestMethod.GET)
    public ResponseEntity<?> findAllUser(){
        try{
            return ResponseEntity.ok(userService.findAll());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Find All User Failed");
        }
    }

    // 유저가 자기 자신 조회할 떄
    @RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public ResponseEntity<?> findUserAsUser(@PathVariable int userId){//,
                                            //Principal principal){
        try{
            String userEmail = userService.findById(userId).getUserEmail();
            UserDTO.FindAsUser findAsUser = userService.findByUserEmail(userEmail);
            System.out.println("userEmail: " + userEmail);
            System.out.println("findAsUser: " + findAsUser);
            return ResponseEntity.ok(findAsUser);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("User Not Found");
        }
    }

    // 다른 유저가 조회할 때
//    @RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
//    public ResponseEntity<?> findUserAsPublic(@PathVariable int userId){
//        try{
//            String userNickname = User.toTableName(userService.findById(userId).getUserNickname());
//            UserDTO.FindAsPublic findAsPublic = userService.findByUserNickname(userNickname);
//            return ResponseEntity.ok(findAsPublic);
//        }
//        catch(Exception e){
//            return ResponseEntity.badRequest().body("User Not Found");
//        }
//    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.PATCH)
    public ResponseEntity<?> updateUser(@PathVariable int userId,
                                        @RequestBody UserDTO.Update update){
        try{
            if(update.getUserEmail() == null){
                update.setUserEmail(userService.findById(userId).getUserEmail());
            }
            userService.update(update);
            return ResponseEntity.ok("User Updated!");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("User Update Failed");
        }
    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable int userId){
        try{
            String userEmail = userService.findById(userId).getUserEmail();
            userService.deleteByUserEmail(userEmail);
            return ResponseEntity.ok("User Deleted!");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("User Delete Failed");
        }
    }
}