package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrowdOrderDetailsService {

   List<CrowdOrderDetailsDTO.FindById> findAll();
   CrowdOrderDetailsDTO.FindById findById(int purchaseId);
   void save(CrowdOrderDetails crowdOrderDetails);
   void update(CrowdOrderDetails crowdOrderDetails);
   void deleteById(int purchaseId);
}
