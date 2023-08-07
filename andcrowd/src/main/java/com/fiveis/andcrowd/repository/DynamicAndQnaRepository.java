package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicAndQnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DynamicAndQnaRepository {
    DynamicAndQnaDTO.FindById findById(int andQnaId);
    void save(DynamicAndQnaDTO.Update andQnaInsertDTO);
    void update(DynamicAndQnaDTO.Update andQnaUpdateDTO);
    void deleteById(int andQnaId);
    void createDynamicTable( String tableName);
}
