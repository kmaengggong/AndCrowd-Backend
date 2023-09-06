package com.fiveis.andcrowd.controller.user;

import com.fiveis.andcrowd.config.jwt.TokenProvider;
import com.fiveis.andcrowd.config.jwt.TokenValidator;
import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.dto.etc.ProjectDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.*;
import com.fiveis.andcrowd.service.etc.TokenService;
import com.fiveis.andcrowd.service.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value="/user")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class DynamicUserController {
    private final UserService userService;
    private final DynamicUserAndService dynamicUserAndService;
    private final DynamicUserFollowService dynamicUserFollowService;
    private final DynamicUserLikeService dynamicUserLikeService;
    private final DynamicUserMakerService dynamicUserMakerService;
    private final DynamicUserOrderService dynamicUserOrderService;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;

//    @Autowired
//    public DynamicUserController(UserService userService,
//                                 DynamicUserAndService dynamicUserAndService,
//                                 DynamicUserFollowService dynamicUserFollowService,
//                                 DynamicUserLikeService dynamicUserLikeService,
//                                 DynamicUserMakerService dynamicUserMakerService,
//                                 DynamicUserOrderService dynamicUserOrderService){
//        this.userService = userService;
//        this.dynamicUserAndService = dynamicUserAndService;
//        this.dynamicUserFollowService = dynamicUserFollowService;
//        this.dynamicUserLikeService = dynamicUserLikeService;
//        this.dynamicUserMakerService = dynamicUserMakerService;
//        this.dynamicUserOrderService = dynamicUserOrderService;
//    }

    // 유저가 참여한 모임
    @RequestMapping(value="{userId}/and", method=RequestMethod.GET)
    public ResponseEntity<?> findUserAnd(@PathVariable int userId,
                                         @CookieValue String access_token){
        System.out.println(tokenProvider.isTokenExpired(access_token));
        // 엑세스 토큰이 유효하지 않음
        if(!tokenProvider.validToken(access_token)){
            // 로그아웃 수행
//            tokenService.deleteAllToken(userId, access_token);
        }
        // 엑세스 토큰 유효기간 지났을 경우
        if(tokenProvider.isTokenExpired(access_token)){
            // 리프레쉬 토큰을 이용해 재발급 시도
            return ResponseEntity.status(401).build();
        }
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        List<AndDTO.Find> andDTOFindList = dynamicUserAndService.findAll(userEmail);
        return ResponseEntity.ok(andDTOFindList);
    }

    @RequestMapping(value="{userId}/and", method=RequestMethod.POST)
    public ResponseEntity<?> saveUserAnd(@PathVariable int userId, @RequestBody DynamicUserAnd dynamicUserAnd){//,
                                        //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        if(!dynamicUserAndService.save(userEmail, dynamicUserAnd)) return ResponseEntity.badRequest().body("Save Failed!");
        return ResponseEntity.ok("UserAnd Saved!");
    }

    @RequestMapping(value="{userId}/and/{andId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserAnd(@PathVariable int userId, @PathVariable int andId){//,
                                            //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        dynamicUserAndService.deleteByAndId(userEmail, andId);
        return ResponseEntity.ok("UserAnd Deleted!");
    }

    // 유저가 팔로우한 유저
    @RequestMapping(value="{userId}/follow", method=RequestMethod.GET)
    public ResponseEntity<?> findUserFollow(@PathVariable int userId){//,
                                            //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        List<UserDTO.FindAsPublic> userDTOFindList = dynamicUserFollowService.findAll(userEmail);
        return ResponseEntity.ok(userDTOFindList);
    }

    @RequestMapping(value="{userId}/follow", method=RequestMethod.POST)
    public ResponseEntity<?> saveUserFollow(@PathVariable int userId, @RequestBody DynamicUserFollow dynamicUserFollow){//,
                                            //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        if(!dynamicUserFollowService.save(userEmail, dynamicUserFollow)) return ResponseEntity.badRequest().body("Save Failed!");
        return ResponseEntity.ok("UserFollow Saved!");
    }

    @RequestMapping(value="{userId}/follow/{fUserId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserFollow(@PathVariable int userId, @PathVariable int fUserId){//,
        //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        dynamicUserFollowService.deleteByAndId(userEmail, fUserId);
        return ResponseEntity.ok("UserFollow Deleted!");
    }

    // 유저가 좋아요 누른 프로젝트
    @RequestMapping(value="{userId}/like", method=RequestMethod.GET)
    public ResponseEntity<?> findUserLike(@PathVariable int userId){//,
                                        //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        List<ProjectDTO.Find> projectDTOFindList = dynamicUserLikeService.findAll(userEmail);
        return ResponseEntity.ok(projectDTOFindList);
    }

    @RequestMapping(value="{userId}/like", method=RequestMethod.POST)
    public ResponseEntity<?> saveUserLike(@PathVariable int userId, @RequestBody DynamicUserLike dynamicUserLike){//,
                                            //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        if(dynamicUserLike.getProjectType() != 0 && dynamicUserLike.getProjectType() != 1)
            return ResponseEntity.badRequest().body("Wrong Value!");
        if(!dynamicUserLikeService.save(userEmail, dynamicUserLike)) return ResponseEntity.badRequest().body("Save Failed!");
        return ResponseEntity.ok("UserLike Saved!");
    }

    @RequestMapping(value="{userId}/like/{projectId}/{projectType}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserLike(@PathVariable int userId,
                                            @PathVariable int projectId,
                                            @PathVariable int projectType){//,
        //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        dynamicUserLikeService.deleteByProjectId(userEmail, projectId, projectType);
        return ResponseEntity.ok("UserLike Deleted!");
    }

    // 유저가 만든 프로젝트
    @RequestMapping(value="{userId}/maker", method=RequestMethod.GET)
    public ResponseEntity<?> findUserMaker(@PathVariable int userId){//,
                                            //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        List<ProjectDTO.Find> projectDTOFindList = dynamicUserMakerService.findAll(userEmail);
        return ResponseEntity.ok(projectDTOFindList);
    }

    @RequestMapping(value="{userId}/maker", method=RequestMethod.POST)
    public ResponseEntity<?> saveUserMaker(@PathVariable int userId, @RequestBody DynamicUserMaker dynamicUserMaker){//,
                                            //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        if(dynamicUserMaker.getProjectType() != 0 && dynamicUserMaker.getProjectType() != 1)
            return ResponseEntity.badRequest().body("Wrong Value!");
        if(!dynamicUserMakerService.save(userEmail, dynamicUserMaker)) return ResponseEntity.badRequest().body("Save Failed!");
        return ResponseEntity.ok("UserMaker Saved!");
    }

    @RequestMapping(value="{userId}/maker/{projectId}/{projectType}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserMaker(@PathVariable int userId,
                                             @PathVariable int projectId,
                                             @PathVariable int projectType){//,
        //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        dynamicUserMakerService.deleteByProjectId(userEmail, projectId, projectType);
        return ResponseEntity.ok("UserMaker Deleted!");
    }

    // 유저의 펀딩 주문 내역
    @RequestMapping(value="{userId}/order", method=RequestMethod.GET)
    public ResponseEntity<?> findUserOrder(@PathVariable int userId){//,
                                            //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        List<CrowdOrderDetailsDTO.FindById> crowdOrderDetailsDTOFindList = dynamicUserOrderService.findAll(userEmail);
        return ResponseEntity.ok(crowdOrderDetailsDTOFindList);
    }

    @RequestMapping(value="{userId}/order", method=RequestMethod.POST)
    public ResponseEntity<?> saveUserOrder(@PathVariable int userId, @RequestBody DynamicUserOrder dynamicUserOrder){//,
                                            //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        if(!dynamicUserOrderService.save(userEmail, dynamicUserOrder)) return ResponseEntity.badRequest().body("Save Failed!");
        return ResponseEntity.ok("UserOrder Saved!");
    }

    @RequestMapping(value="{userId}/order/{orderId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserOrder(@PathVariable int userId,
                                             @PathVariable int orderId){//,
        //Principal principal){
        String userEmail = User.toTableName(userService.findById(userId).getUserEmail());
        dynamicUserOrderService.deleteByOrderId(userEmail, orderId);
        return ResponseEntity.ok("UserOrder Deleted!");
    }
}
