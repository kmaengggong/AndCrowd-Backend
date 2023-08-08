package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.CrowdOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrowdOrderDetailsJPARepository extends JpaRepository<CrowdOrderDetails, Integer> {

    List<CrowdOrderDetails> findAllByUserId(int purchaseId);

}
