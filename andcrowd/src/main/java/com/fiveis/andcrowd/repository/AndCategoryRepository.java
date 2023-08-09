package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.AndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AndCategoryRepository extends JpaRepository<AndCategory, Integer> {

}
