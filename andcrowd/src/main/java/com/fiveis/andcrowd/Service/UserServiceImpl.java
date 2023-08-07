package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.UserDTO;
import com.fiveis.andcrowd.entity.User;
import com.fiveis.andcrowd.repository.UserJPARepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserJPARepository userJPARepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserJPARepository userJPARepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userJPARepository = userJPARepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<UserDTO.UserFindDTO> findAll(){
        List<User> userList = userJPARepository.findAll();
        List<UserDTO.UserFindDTO> userFindDTOList = new ArrayList<>();
        for (User user : userList) {
            UserDTO.UserFindDTO dto = user.toFindDTO();
            userFindDTOList.add(dto);
        }
        return userFindDTOList;
    }

    public List<UserDTO.UserFindByIdDTO> findAllByUserKorName(String userKorName){
        List<User> userList = userJPARepository.findAllByUserKorName(userKorName);
        List<UserDTO.UserFindByIdDTO> userFindDTOList = new ArrayList<>();
        for (User user : userList) {
            UserDTO.UserFindByIdDTO dto = user.toFindByIdDTO();
            userFindDTOList.add(dto);
        }
        return userFindDTOList;
    }

    public UserDTO.UserFindByIdDTO findById(String userId){
        if(userJPARepository.findById(userId).isEmpty()) return null;
        return userJPARepository.findById(userId).get().toFindByIdDTO();
    }

    public UserDTO.UserFindDTO findByUserEmail(String userEmail){
        if(userJPARepository.findByUserEmail(userEmail).isEmpty()) return null;
        return userJPARepository.findByUserEmail(userEmail).get().toFindDTO();
    }

    public UserDTO.UserFindDTO findByUserNickname(String userNickname){
        if(userJPARepository.findByUserNickname(userNickname).isEmpty()) return null;
        return userJPARepository.findByUserNickname(userNickname).get().toFindDTO();
    }

    @Transactional
    public void deleteByUserEmail(String userEmail) {
        userJPARepository.deleteByUserEmail(userEmail);
    }

    @Transactional
    public void save(User user) {
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        userJPARepository.save(user);
    }

    @Transactional
    public void update(UserDTO.UserUpdateDTO userUpdateDTO) {
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
