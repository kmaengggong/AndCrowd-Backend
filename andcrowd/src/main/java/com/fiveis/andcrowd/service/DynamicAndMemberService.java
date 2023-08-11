package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicAndMemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicAndMemberService {
    void save(DynamicAndMemberDTO.Update dynamicAndMemberDTOUpdate);

    DynamicAndMemberDTO.FindByMemberId findById(int memberId);

    List<DynamicAndMemberDTO.FindByMemberId> findAll();

    void deleteById(int memberId);

    void createAndMemberTable();
    void dropAndMemberTable();

    void insertTestData();
}
