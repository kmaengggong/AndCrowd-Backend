package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.CrowdOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrowdOrderDetailsJPARepository extends JpaRepository<CrowdOrderDetails, Integer> {
    List<CrowdOrderDetails> findAllByUserId(int purchaseId);

}
