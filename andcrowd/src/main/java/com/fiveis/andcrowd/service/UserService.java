package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.UserFindByIdDTO;
import com.fiveis.andcrowd.entity.User;

public interface UserService {
    UserFindByIdDTO findById(int userId);

    void deleteById(int userId);

    void save(User user);

    void update(User user);
}
