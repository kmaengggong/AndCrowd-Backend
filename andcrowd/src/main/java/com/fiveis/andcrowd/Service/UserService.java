package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.entity.User;
import com.fiveis.andcrowd.repository.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserJPARepository userJPARepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserJPARepository userJPARepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userJPARepository = userJPARepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<User> findById(int userId){
        return userJPARepository.findById(userId);
    }

    public void deleteById(int userId) {
        userJPARepository.deleteById(userId);
    }

    public void save(User user) {
        user.updateUser(bCryptPasswordEncoder.encode(user.getUserPassword()), user.getUserPhone(), user.getUserProfileImg());
        userJPARepository.save(user);
    }

    public void update(User user){
        int userId = user.getUserId();
        User originUser = userJPARepository.findById(userId).get();
        originUser.updateUser(user.getUserPassword(), user.getUserPhone(), user.getUserProfileImg());
        userJPARepository.save(originUser);
    }

    public User getByCredentials(String userEmail){
        return userJPARepository.findByUserEmail(userEmail);
    }
}
