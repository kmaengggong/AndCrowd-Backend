package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicAndMemberDTO;
import com.fiveis.andcrowd.repository.DynamicAndMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicAndMemberServiceImpl implements DynamicAndMemberService {

    private final DynamicAndMemberRepository dynamicAndMemberRepository;

    @Autowired
    public DynamicAndMemberServiceImpl(DynamicAndMemberRepository dynamicAndMemberRepository) {
        this.dynamicAndMemberRepository = dynamicAndMemberRepository;
    }

    @Override
    public void save(DynamicAndMemberDTO.Update dynamicAndMemberDTOUpdate) {
        dynamicAndMemberRepository.save(dynamicAndMemberDTOUpdate);
    }

    @Override
    public DynamicAndMemberDTO.FindByMemberId findById(int memberId) {
        return dynamicAndMemberRepository.findById(memberId);
    }

    @Override
    public List<DynamicAndMemberDTO.FindByMemberId> findAll() {
        return dynamicAndMemberRepository.findAll();
    }

    @Override
    public void deleteById(int memberId) {
        dynamicAndMemberRepository.deleteById(memberId);
    }

    @Override
    public void createAndMemberTable() {
        dynamicAndMemberRepository.createAndMemberTable();
    }

    @Override
    public void dropAndMemberTable() {
        dynamicAndMemberRepository.dropAndMemberTable();
    }

    @Override
    public void insertTestData() {
        dynamicAndMemberRepository.insertTestData();
    }
}
