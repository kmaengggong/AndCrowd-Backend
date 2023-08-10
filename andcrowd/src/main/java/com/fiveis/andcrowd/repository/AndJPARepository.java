package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.AndDTO;
import com.fiveis.andcrowd.entity.And;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AndJPARepository extends JpaRepository<And, Integer> {
    List<AndDTO.FindAllByUserId> findAllByUserId(int userId);

    @Modifying
    @Query("UPDATE And a SET a.isDeleted = true WHERE a.andId = :andId")
    void softDeleteById(@Param("andId") Integer andId);
    }
