package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdJPARepository extends JpaRepository<Ad, Integer> {
}
