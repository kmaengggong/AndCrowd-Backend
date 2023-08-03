package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.And;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AndRepository extends JpaRepository<And, Integer> {
    List<And> findAllByUserId(int userId);
}
