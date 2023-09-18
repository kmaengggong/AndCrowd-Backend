package com.fiveis.andcrowd.repository.and;

import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.entity.and.And;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    @Query("SELECT COUNT(a) FROM And a WHERE a.andTitle LIKE %:searchKeyword% AND a.andStatus IN (1, 3) AND a.isDeleted = false")
    int totalCount(@Param("searchKeyword") String searchKeyword);

    // 현재 날짜 이전의 andEndDate와 andStatus가 특정 값(3)이 아닌 엔티티 검색
    @Query("SELECT a FROM And a WHERE a.andEndDate < :endDate AND a.andStatus <> :status")
    List<And> findExpiredAnds(@Param("endDate") LocalDateTime endDate, @Param("status") int status);
}
