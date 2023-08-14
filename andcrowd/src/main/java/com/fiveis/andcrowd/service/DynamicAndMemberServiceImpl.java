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
    public DynamicAndMemberDTO.FindByAndMemberId findByAndMemberId(int andId, int memberId) {
        return dynamicAndMemberRepository.findByAndMemberId(andId, memberId);
    }

    @Override
    public List<DynamicAndMemberDTO.FindByAndMemberId> findAll(int andId) {
        return dynamicAndMemberRepository.findAll(andId);
    }

    @Override
    public void deleteById(int andId, int memberId) {
        dynamicAndMemberRepository.deleteById(andId, memberId);
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
