package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CrowdOrderDetailsService {

   List<CrowdOrderDetailsDTO.FindById> findAll();//int crowdId);
   Optional<CrowdOrderDetailsDTO.FindById> findById(int crowdId);
   void save(CrowdOrderDetails crowdOrderDetails);
   void update(CrowdOrderDetailsDTO.Update updateDTO);
   void deleteById(int purchaseId);
   public CrowdOrderDetailsDTO.FindById convertToFindByIdDTO(CrowdOrderDetails crowdOrderDetails);
}
