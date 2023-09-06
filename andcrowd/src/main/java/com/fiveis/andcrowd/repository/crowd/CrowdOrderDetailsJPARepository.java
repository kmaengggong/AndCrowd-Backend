package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrowdOrderDetailsJPARepository extends JpaRepository<CrowdOrderDetails, Integer> {
    List<CrowdOrderDetails> findAllByCrowdId(int crowdId);

    // 결제 취소시 상태를 환불로 바꿔주는 기능
    @Modifying
    @Query("UPDATE CrowdOrderDetails c SET c.purchaseStatus = '환불' WHERE c.purchaseId = :orderId")
    void setRefundStatusForOrder(@Param("orderId") Integer orderId);
}
