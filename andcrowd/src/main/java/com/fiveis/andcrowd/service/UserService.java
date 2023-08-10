package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.UserDTO;
import com.fiveis.andcrowd.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO.FindAsPublic> findAll();
    List<UserDTO.FindAsUser> findAllByUserKorName(String userKorName);
    UserDTO.FindAsAdmin findById(int userId);
    UserDTO.FindAsUser findByUserEmail(String userEmail);
    UserDTO.FindAsPublic findByUserNickname(String userNickname);
    void deleteByUserEmail(String userEmail);
    void save(User user);
    void update(UserDTO.Update user);
    User getByCredentials(String userEmail);
}