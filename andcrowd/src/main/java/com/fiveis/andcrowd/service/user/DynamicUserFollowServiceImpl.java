package com.fiveis.andcrowd.service.user;

import com.fiveis.andcrowd.dto.user.DynamicUserFollowDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.DynamicUserFollow;
import com.fiveis.andcrowd.repository.user.DynamicUserFollowRepository;
import com.fiveis.andcrowd.repository.user.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicUserFollowServiceImpl implements DynamicUserFollowService{
    private static DynamicUserFollowRepository dynamicUserFollowRepository;
    private static UserJPARepository userJPARepository;

    @Autowired
    public DynamicUserFollowServiceImpl(DynamicUserFollowRepository dynamicUserFollowRepository,
                                        UserJPARepository userJPARepository){
        this.dynamicUserFollowRepository = dynamicUserFollowRepository;
        this.userJPARepository = userJPARepository;
    }

    public List<UserDTO.FindAsPublic> findAll(String userEmail){
        List<DynamicUserFollowDTO.Find> findList = dynamicUserFollowRepository.findAll(userEmail);
        List<UserDTO.FindAsPublic> userList = new ArrayList<>();
        for(DynamicUserFollowDTO.Find find : findList){
            userList.add(UserDTO.convertToFindAsPublicDTO(userJPARepository.findById(find.getUserId()).get()));
        }
        return userList;
    }

    public DynamicUserFollowDTO.Find findById(String userEmail, int uFollowId){
        return dynamicUserFollowRepository.findById(userEmail, uFollowId);
    }

    public void save(String userEmail, DynamicUserFollow dynamicUserFollow){
        if(dynamicUserFollowRepository.findByUserId(userEmail, dynamicUserFollow.getUserId()) != null) return;
        dynamicUserFollowRepository.save(userEmail, dynamicUserFollow);
    }

    public void deleteById(String userEmail, int uFollowId){
        dynamicUserFollowRepository.deleteById(userEmail, uFollowId);
    }

    public void deleteByAndId(String userEmail, int userId){
        dynamicUserFollowRepository.deleteByUserId(userEmail, userId);
    }
}
