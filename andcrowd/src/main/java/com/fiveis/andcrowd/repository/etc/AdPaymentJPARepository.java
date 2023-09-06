package com.fiveis.andcrowd.repository.etc;

import com.fiveis.andcrowd.entity.etc.AdPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdPaymentJPARepository extends JpaRepository<AdPayment, Integer> {
    List<AdPayment> findAllByUserId(int userId);

    // 결제 취소시 상태를 환불로 변경하는 기능
    @Modifying
    @Query("UPDATE AdPayment a SET a.adPaymentStatus = 2 WHERE a.adId = :adId")
    void setAdPaymentStatusByAdId(@Param("adId") Integer adId);
}
