package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.UserDTO;
import com.fiveis.andcrowd.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserDTO.UserFindDTO> findAll();
    List<UserDTO.UserFindByIdDTO> findAllByUserKorName(String userKorName);
    UserDTO.UserFindByIdDTO findById(String userId);
    UserDTO.UserFindDTO findByUserEmail(String userEmail);
    UserDTO.UserFindDTO findByUserNickname(String userNickname);
    void deleteByUserEmail(String userEmail);
    void save(User user);
    void update(UserDTO.UserUpdateDTO user);
    User getByCredentials(String userEmail);
}
