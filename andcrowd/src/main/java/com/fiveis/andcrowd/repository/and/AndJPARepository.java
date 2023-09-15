package com.fiveis.andcrowd.repository.and;

import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.entity.and.And;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AndJPARepository extends JpaRepository<And, Integer>, AndQueryRepository {
    List<AndDTO.FindAllByUserId> findAllByUserId(int userId);
//    List<AndDTO.Find> findAllByIsDeletedFalse();
    List<And> findAllByIsDeletedFalse();

    @Modifying
    @Query("UPDATE And a SET a.andViewCount = a.andViewCount + 1 WHERE a.andId = :andId")
    int updateView(@Param("andId") Integer andId);


    @Modifying
    @Query("UPDATE And a SET a.andLikeCount = a.andLikeCount + 1 WHERE a.andId = :andId")
    void increaseLike(@Param("andId") Integer andId);

    @Modifying
    @Query("UPDATE And a SET a.andLikeCount = a.andLikeCount - 1 WHERE a.andId = :andId")
    void decreaseLike(@Param("andId") Integer andId);

}
