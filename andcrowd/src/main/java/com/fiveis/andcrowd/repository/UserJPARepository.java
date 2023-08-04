package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer> {
}
