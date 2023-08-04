package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.UserFindByIdDTO;
import com.fiveis.andcrowd.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findById(int userId);

    void deleteById(int userId);

    void save(User user);

    void update(User user);
}
