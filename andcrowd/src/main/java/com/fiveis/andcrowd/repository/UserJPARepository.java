package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Integer> {
    User findByUserEmail(String userEmail);
}
