package com.fiveis.andcrowd.service.user;

import com.fiveis.andcrowd.dto.user.DynamicUserFollowDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.DynamicUserFollow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicUserFollowService {
    List<UserDTO.FindAsPublic> findAll(String userEmail);  // 유저의 프로필 이미지, 닉네임 가져옴
    DynamicUserFollowDTO.Find findById(String userEmail, int uFollowId);
    boolean save(String userEmail, DynamicUserFollow dynamicUserFollow);
    void deleteById(String userEmail, int uFollowId);
    void deleteByAndId(String userEmail, int userId);
}
