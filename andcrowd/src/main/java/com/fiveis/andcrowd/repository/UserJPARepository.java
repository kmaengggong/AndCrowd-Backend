package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, String> {
    List<User> findAllByUserKorName(String userKorName);
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserNickname(String userNickname);
    void deleteByUserEmail(String userEmail);
}