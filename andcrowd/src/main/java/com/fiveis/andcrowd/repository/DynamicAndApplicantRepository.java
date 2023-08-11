package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicAndApplicantDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicAndApplicantRepository {
    List<DynamicAndApplicantDTO.FindById> findAll(int andId);
    List<DynamicAndApplicantDTO.FindById> findAllNotDeleted(int andId);
    DynamicAndApplicantDTO.FindById findByAndApplicantId(@Param("and_id") int andId, @Param("andApplyId") int andApplyId);
    List<DynamicAndApplicantDTO.FindById> findByUserId(@Param("and_id") int andId, @Param("userId") int userId);
    List<DynamicAndApplicantDTO.FindById> findByAndRoleId(@Param("and_id") int andId, @Param("andRoleId") int andRoleId);

    void save(DynamicAndApplicantDTO.Update andApplicantInsertDTO);
    void update(DynamicAndApplicantDTO.Update andApplicantUpdateDTO);
    void deleteByAndApplicantId(@Param("andId") int andId, @Param("andApplyId") int andApplyId);

    void createAndApplicantTable ();
    void dropAndApplicantTable();
    void insertTestData();


}
