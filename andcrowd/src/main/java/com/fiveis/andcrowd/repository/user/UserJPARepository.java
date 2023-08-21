package com.fiveis.andcrowd.repository.user;

import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Integer> {
    List<UserDTO.FindAsUser> findAllByUserKorName(String userKorName);
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserId(int userId);
    Optional<UserDTO.FindAsPublic> findByUserNickname(String userNickname);
    void deleteByUserEmail(String userEmail);
}