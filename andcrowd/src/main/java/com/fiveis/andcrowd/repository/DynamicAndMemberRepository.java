package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicAndMemberRepository {

    void save(DynamicAndMemberDTO.Update dynamicAndMemberDTOUpdate);

    DynamicAndMemberDTO.FindByMemberId findById(@Param("memberId") int memberId);

    List<DynamicAndMemberDTO.FindByMemberId> findAll();

    void deleteById(@Param("memberId") int memberId);

    void createAndMemberTable ();
    void dropAndMemberTable();

    void insertTestData();
}