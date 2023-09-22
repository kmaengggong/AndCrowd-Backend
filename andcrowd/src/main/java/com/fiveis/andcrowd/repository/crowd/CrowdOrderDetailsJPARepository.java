package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrowdOrderDetailsJPARepository extends JpaRepository<CrowdOrderDetails, Integer> {
    List<CrowdOrderDetails> findAllByCrowdId(int crowdId);

    @Query("SELECT c.rewardId AS rewardId, COUNT(c.rewardId) AS rewardCounts FROM CrowdOrderDetails c WHERE c.crowdId = :crowdId AND c.purchaseStatus = '결제완료' GROUP BY c.rewardId")
    List<CrowdOrderDetailsDTO.rewardCounts> countRewardsByCrowdId(@Param("crowdId") int crowdId);
}
