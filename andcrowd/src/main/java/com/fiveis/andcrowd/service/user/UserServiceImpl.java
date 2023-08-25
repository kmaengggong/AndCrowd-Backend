package com.fiveis.andcrowd.service.user;

import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.repository.user.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserJPARepository userJPARepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private DynamicUserAndRepository dynamicUserAndRepository;
    private DynamicUserFollowRepository dynamicUserFollowRepository;
    private DynamicUserLikeRepository dynamicUserLikeRepository;
    private DynamicUserMakerRepository dynamicUserMakerRepository;
    private DynamicUserOrderRepository dynamicUserOrderRepository;

    @Autowired
    public UserServiceImpl(UserJPARepository userJPARepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           DynamicUserAndRepository dynamicUserAndRepository,
                           DynamicUserFollowRepository dynamicUserFollowRepository,
                           DynamicUserLikeRepository dynamicUserLikeRepository,
                           DynamicUserMakerRepository dynamicUserMakerRepository,
                           DynamicUserOrderRepository dynamicUserOrderRepository){
        this.userJPARepository = userJPARepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dynamicUserAndRepository = dynamicUserAndRepository;
        this.dynamicUserFollowRepository = dynamicUserFollowRepository;
        this.dynamicUserLikeRepository = dynamicUserLikeRepository;
        this.dynamicUserMakerRepository = dynamicUserMakerRepository;
        this.dynamicUserOrderRepository = dynamicUserOrderRepository;
    }

    public List<UserDTO.FindAsPublic> findAll(){
        List<User> userList = userJPARepository.findAll();
        List<UserDTO.FindAsPublic> userFindDTOList = new ArrayList<>();
        for (User user : userList) {
            UserDTO.FindAsPublic dto = UserDTO.convertToFindAsPublicDTO(user);
            userFindDTOList.add(dto);
        }
        return userFindDTOList;
    }

    public List<UserDTO.FindAsUser> findAllByUserKorName(String userKorName){
        return userJPARepository.findAllByUserKorName(userKorName);
    }

    public UserDTO.FindAsAdmin findById(int userId){
        if(userJPARepository.findById(userId).isEmpty()) return null;
        return UserDTO.convertToFindAsAdminDTO(userJPARepository.findById(userId).get());
    }

    public UserDTO.FindAsUser findByUserEmail(String userEmail){
        if(userJPARepository.findByUserEmail(userEmail).isEmpty()) return null;
        return UserDTO.convertToFindAsUserDTO(userJPARepository.findByUserEmail(userEmail).get());
    }

    public UserDTO.FindAsPublic findByUserNickname(String userNickname){
        if(userJPARepository.findByUserNickname(userNickname).isEmpty()) return null;
        return UserDTO.convertToFindAsPublicDTO(userJPARepository.findByUserNickname(userNickname).get());
    }

    @Transactional
    public void deleteByUserEmail(String userEmail) {
        userJPARepository.deleteByUserEmail(userEmail);
    }

    @Transactional
    public void save(User user) {
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        userJPARepository.save(user);

        // User 생성 시 동적 테이블 자동 생성
        String userEmail = User.toTableName(user.getUserEmail());
        dynamicUserAndRepository.createDynamicUserAndTable(userEmail);
        dynamicUserFollowRepository.createDynamicUserFollowTable(userEmail);
        dynamicUserLikeRepository.createDynamicUserLikeTable(userEmail);
        dynamicUserMakerRepository.createDynamicUserMakerTable(userEmail);
        dynamicUserOrderRepository.createDynamicUserOrderTable(userEmail);
    }

    @Transactional
    public void update(UserDTO.Update userUpdateDTO) {
        User user = userJPARepository.findByUserEmail(userUpdateDTO.getUserEmail()).get();

        if(userUpdateDTO.getUserPassword() != null){
            user.setUserPassword(bCryptPasswordEncoder.encode(userUpdateDTO.getUserPassword()));
        }
        if(userUpdateDTO.getUserNickname() != null){
            user.setUserNickname(userUpdateDTO.getUserNickname());
        }
        if(userUpdateDTO.getUserPhone() != null){
            user.setUserPhone(userUpdateDTO.getUserPhone());
        }
        if(userUpdateDTO.getUserProfileImg() != null){
            user.setUserProfileImg(userUpdateDTO.getUserProfileImg());
        }

        userJPARepository.save(user);
    }

    public User getByCredentials(String userEmail){
        if(userJPARepository.findByUserEmail(userEmail).isEmpty()) return null;
        return userJPARepository.findByUserEmail(userEmail).get();
    }
}
