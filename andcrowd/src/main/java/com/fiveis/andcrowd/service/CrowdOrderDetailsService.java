package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.Crowd;
import com.fiveis.andcrowd.entity.CrowdOrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CrowdOrderDetailsService {

   List<CrowdOrderDetailsDTO.FindById> findAll();
   CrowdOrderDetailsDTO.FindById findById(int purchaseId);
   void save(CrowdOrderDetails crowdOrderDetails);
   void update(CrowdOrderDetailsDTO crowdOrderDetailsDTO);
   void deleteById(int purchaseId);
}
