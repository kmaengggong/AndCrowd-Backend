package com.fiveis.andcrowd.service_;

import com.fiveis.andcrowd.dto.AdDTO;
import com.fiveis.andcrowd.entity.Ad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdService {
    List<AdDTO.Find> findAll();
    AdDTO.Find findById(int adId);
    void save(Ad ad);
    void update(AdDTO.Update update);
    void deleteById(int adId);
}
