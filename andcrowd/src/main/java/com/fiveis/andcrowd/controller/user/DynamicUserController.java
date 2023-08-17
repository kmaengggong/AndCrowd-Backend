package com.fiveis.andcrowd.controller.user;

import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.dto.etc.ProjectDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class DynamicUserController {
    private final UserService userService;
    private final DynamicUserAndService dynamicUserAndService;
    private final DynamicUserFollowService dynamicUserFollowService;
    private final DynamicUserLikeService dynamicUserLikeService;
    private final DynamicUserMakerService dynamicUserMakerService;
    private final DynamicUserOrderService dynamicUserOrderService;

    @Autowired
    public DynamicUserController(UserService userService,
                                 DynamicUserAndService dynamicUserAndService,
                                 DynamicUserFollowService dynamicUserFollowService,
                                 DynamicUserLikeService dynamicUserLikeService,
                                 DynamicUserMakerService dynamicUserMakerService,
                                 DynamicUserOrderService dynamicUserOrderService){
        this.userService = userService;
        this.dynamicUserAndService = dynamicUserAndService;
        this.dynamicUserFollowService = dynamicUserFollowService;
        this.dynamicUserLikeService = dynamicUserLikeService;
        this.dynamicUserMakerService = dynamicUserMakerService;
        this.dynamicUserOrderService = dynamicUserOrderService;
    }

    @RequestMapping(value="{userId}/and", method=RequestMethod.GET)
    public ResponseEntity<?> getUserAnd(@PathVariable int userId){//,
                                        //Principal principal){
        String userEmail = userService.findById(userId).getUserEmail();
        List<AndDTO.Find> andDTOFindList = dynamicUserAndService.findAll(userService.toTableName(userEmail));
        return ResponseEntity.ok(andDTOFindList);
    }

    @RequestMapping(value="{userId}/follow", method=RequestMethod.GET)
    public ResponseEntity<?> getUserFollow(@PathVariable int userId){
        String userEmail = userService.findById(userId).getUserEmail();
        List<UserDTO.FindAsPublic> userDTOFindList = dynamicUserFollowService.findAll(userService.toTableName(userEmail));
        return ResponseEntity.ok(userDTOFindList);
    }

    @RequestMapping(value="{userId}/like", method=RequestMethod.GET)
    public ResponseEntity<?> getUserLike(@PathVariable int userId){
        String userEmail = userService.findById(userId).getUserEmail();
        List<ProjectDTO.Find> projectDTOFindList = dynamicUserLikeService.findAll(userService.toTableName(userEmail));
        return ResponseEntity.ok(projectDTOFindList);
    }

    @RequestMapping(value="{userId}/maker", method=RequestMethod.GET)
    public ResponseEntity<?> getUserMaker(@PathVariable int userId){
        String userEmail = userService.findById(userId).getUserEmail();
        List<ProjectDTO.Find> projectDTOFindList = dynamicUserMakerService.findAll(userService.toTableName(userEmail));
        return ResponseEntity.ok(projectDTOFindList);
    }

    @RequestMapping(value="{userId}/order", method=RequestMethod.GET)
    public ResponseEntity<?> getUserOrder(@PathVariable int userId){
        String userEmail = userService.findById(userId).getUserEmail();
        List<CrowdOrderDetailsDTO.FindById> crowdOrderDetailsDTOFindList = dynamicUserOrderService.findAll(userService.toTableName(userEmail));
        return ResponseEntity.ok(crowdOrderDetailsDTOFindList);
    }
}
