package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.And;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AndJPARepository extends JpaRepository<And, Integer> {
    List<And> findAllByUserId(int userId);

    And findByAndId(int andId);

    void deleteByAndId(int andId);
}
