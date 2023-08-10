package com.fiveis.andcrowd.service_;

import com.fiveis.andcrowd.dto.DynamicAndQnaDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicAndQnaService {
    List<DynamicAndQnaDTO.FindById> findAll(int andId);
    DynamicAndQnaDTO.FindById findByAndQnaId(int andId, int andQnaId);
    void save(DynamicAndQnaDTO.Update andQnaInsertDTO);
    void update(DynamicAndQnaDTO.Update andQnaUpdateDTO);
    void deleteByAndQnaId(int andId, int andQnaId);

}
