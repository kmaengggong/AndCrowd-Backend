package com.fiveis.andcrowd.controller.user;

import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 유저가 자기 자신 조회할 떄
    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public ResponseEntity<?> findUserAsUser(@PathVariable int userId){//,
                                            //Principal principal){
        try{
            String userNickname = userService.findById(userId).getUserNickname();
            UserDTO.FindAsPublic findAsPublic = userService.findByUserNickname(userNickname);
            return ResponseEntity.ok(findAsPublic);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("User Not Found");
        }
    }

    // 다른 유저가 조회할 때
//    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
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

    @RequestMapping(value="/{userId}", method=RequestMethod.PATCH)
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

    @RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
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