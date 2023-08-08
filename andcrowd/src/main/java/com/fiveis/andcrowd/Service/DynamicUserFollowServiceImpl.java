package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicUserFollowDTO;
import com.fiveis.andcrowd.entity.User;
import com.fiveis.andcrowd.repository.DynamicUserFollowRepository;
import com.fiveis.andcrowd.repository.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<User> findAll(String tableName){
        List<DynamicUserFollowDTO.Find> findList = dynamicUserFollowRepository.findAll(tableName);
        List<User> userList = new ArrayList<>();
        for(DynamicUserFollowDTO.Find find : findList){
            userList.add(userJPARepository.findById(find.getUserId()).get());
        }
        return userList;
    }

    public DynamicUserFollowDTO.Find findById(Map<String, ?> map){
        return dynamicUserFollowRepository.findById(map);
    }

    public void save(Map<String, ?> map){
        dynamicUserFollowRepository.save(map);
    }

    public void deleteById(Map<String, ?> map){
        dynamicUserFollowRepository.deleteById(map);
    }
}
