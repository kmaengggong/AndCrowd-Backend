package com.fiveis.andcrowd.controller.user;

import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ResponseEntity<?> findAllUser(){
        try{
            return ResponseEntity.ok(userService.findAll());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Find All User Failed");
        }
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public ResponseEntity<?> findUserAsUser(@PathVariable int userId){
        try{
            // 권한 확인
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDTO.FindAsAdmin findAsAdmin = userService.findById(userId);
            String userEmail = findAsAdmin.getUserEmail();

            // 본인인 경우
            if(authentication.getName().equals(userEmail)){
                return ResponseEntity.ok(UserDTO.convertToFindAsUserDTO(userService.findByUserEmail(userEmail)));
            }

            // 본인이 아닌 경우
            return ResponseEntity.ok(UserDTO.convertToFindAsPublicDTO(userService.findByUserEmail(userEmail)));

        } catch(Exception e){
            return ResponseEntity.badRequest().body("User Not Found");
        }
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.PATCH)
    public ResponseEntity<?> updateUser(@PathVariable int userId,
                                        @RequestBody UserDTO.Update update){
        // 권한 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> authorityList = authentication
                .getAuthorities()
                .stream()
                .map(authority -> authority.toString())
                .toList();

        // 본인이 아니라면 컷
        if(!authorityList.contains("ROLE_USER") ||
                !authentication.getName().equals(userService.findById(userId).getUserEmail())){
            return ResponseEntity.badRequest().body("Permission Denied");
        }

        try{
            if(update.getUserEmail() == null){
                update.setUserEmail(userService.findById(userId).getUserEmail());
            }
            System.out.println(update);
            userService.update(update);
            return ResponseEntity.ok("User Updated!");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("User Update Failed");
        }
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable int userId){
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
            if(!authentication.getName().equals(userService.findById(userId).getUserEmail())){
                return ResponseEntity.badRequest().body("Permission Denied");
            }
        }
        // 로그인 안한 경우
        else{
            System.out.println("!!! 비로그인 유저 혹은 AccessToken 만료");
            return ResponseEntity.badRequest().body("Permission Denied");
        }
        try{
            String userEmail = userService.findById(userId).getUserEmail();
            userService.deleteByUserEmail(userEmail);
            return ResponseEntity.ok("User Deleted!");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("User Delete Failed");
        }
    }
}