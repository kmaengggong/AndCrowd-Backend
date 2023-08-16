package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserFollowDTO;
import com.fiveis.andcrowd.dto.UserDTO;
import com.fiveis.andcrowd.entity.DynamicUserFollow;
import com.fiveis.andcrowd.repository.DynamicUserFollowRepository;
import com.fiveis.andcrowd.repository.UserJPARepository;
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
            userList.add(UserDTO.toFindAsPublicDTO(userJPARepository.findById(find.getUserId()).get()));
        }
        return userList;
    }

    public DynamicUserFollowDTO.Find findById(String userEmail, int uFollowId){
        return dynamicUserFollowRepository.findById(userEmail, uFollowId);
    }

    public void save(String userEmail, DynamicUserFollow dynamicUserFollow){
        dynamicUserFollowRepository.save(userEmail, dynamicUserFollow);
    }

    public void deleteById(String userEmail, int uFollowId){
        dynamicUserFollowRepository.deleteById(userEmail, uFollowId);
    }
}
