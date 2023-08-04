package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.AndFindAllByUserIdDTO;
import com.fiveis.andcrowd.dto.AndFindByIdDTO;
import com.fiveis.andcrowd.entity.And;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AndJPARepository extends JpaRepository<And, Integer> {
    List<AndFindAllByUserIdDTO> findAllByUserId(int userId);

    Optional<And> findById(int andId);

    }
