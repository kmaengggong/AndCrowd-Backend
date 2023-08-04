package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.entity.User;
import com.fiveis.andcrowd.repository.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserJPARepository userJPARepository;

    @Autowired
    public UserServiceImpl(UserJPARepository userJPARepository){
        this.userJPARepository = userJPARepository;
    }

    Optional<User> findById(int userId){
        return userJPARepository.findById(userId);
    }

    void deleteById(int userId) {
        userJPARepository.deleteById(userId);
    }

    void save(User user) {
        userJPARepository.save(user);
    }

    void update(User user){
        int userId = user.getUserId();
        User originUser = userJPARepository.findById(userId).get();
        originUser.updateUser(user.getUserPassword(), user.getUserPhone(), user.getUserProfileImg());
        userJPARepository.save(originUser);
    }
}
