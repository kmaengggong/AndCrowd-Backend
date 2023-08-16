package com.fiveis.andcrowd.repository.and;

import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.entity.and.And;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AndJPARepository extends JpaRepository<And, Integer> {
    List<AndDTO.FindAllByUserId> findAllByUserId(int userId);
//    List<AndDTO.Find> findAllByIsDeletedFalse();
    List<And> findAllByIsDeletedFalse();
    }
