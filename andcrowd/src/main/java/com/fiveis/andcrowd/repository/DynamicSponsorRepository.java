package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicSponsorDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DynamicSponsorRepository {

    List<DynamicSponsorDTO.findById> findAll();

    DynamicSponsorDTO.findById findById(int sponsorId);

    void save(DynamicSponsorDTO dynamicSponsorDTO);

}
