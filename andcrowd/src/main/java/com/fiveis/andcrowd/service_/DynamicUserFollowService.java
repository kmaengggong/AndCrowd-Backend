package com.fiveis.andcrowd.service_;

import com.fiveis.andcrowd.dto.DynamicUserFollowDTO;
import com.fiveis.andcrowd.dto.UserDTO;
import com.fiveis.andcrowd.entity.DynamicUserFollow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicUserFollowService {
    List<UserDTO.FindAsPublic> findAll(String userEmail);  // 유저의 프로필 이미지, 닉네임 가져옴
    DynamicUserFollowDTO.Find findById(String userEmail, int uFollowId);
    void save(String userEmail, DynamicUserFollow dynamicUserFollow);
    void deleteById(String userEmail, int uFollowId);
}
