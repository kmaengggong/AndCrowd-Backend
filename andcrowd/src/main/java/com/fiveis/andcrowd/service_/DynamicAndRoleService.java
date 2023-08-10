package com.fiveis.andcrowd.service_;

import com.fiveis.andcrowd.dto.DynamicAndRoleDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicAndRoleService {
    List<DynamicAndRoleDTO.FindById> findAll(int andId);
    DynamicAndRoleDTO.FindById findByAndRoleId(@Param("and_id") int andId, @Param("andRoleId") int andRoleId);
    void save(DynamicAndRoleDTO.Update andRoleInsertDTO);
    void update(DynamicAndRoleDTO.Update andRoleUpdateDTO);
    void deleteByAndRoleId(@Param("andId") int andId, @Param("andRoleId") int andRoleId);

}
