package com.fiveis.andcrowd.repository.and;

import com.fiveis.andcrowd.entity.and.AndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AndCategoryRepository extends JpaRepository<AndCategory, Integer> {

}
