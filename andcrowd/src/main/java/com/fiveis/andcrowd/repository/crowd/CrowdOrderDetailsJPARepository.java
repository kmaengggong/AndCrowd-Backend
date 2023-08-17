package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrowdOrderDetailsJPARepository extends JpaRepository<CrowdOrderDetails, Integer> {

    List<CrowdOrderDetailsDTO.FindById> findAllByUserId(int userId);

}
