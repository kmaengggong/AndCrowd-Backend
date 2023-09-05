package com.fiveis.andcrowd.service.and;

import com.fiveis.andcrowd.dto.and.DynamicAndMemberDTO;
import com.fiveis.andcrowd.dto.and.DynamicAndQnaDTO;
import com.fiveis.andcrowd.dto.and.DynamicAndQnaReplyDTO;
import com.fiveis.andcrowd.repository.and.DynamicAndMemberRepository;
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
    public List<DynamicAndMemberDTO.FindByAndMemberId> findAllNotDeleted(int andId) {
        return dynamicAndMemberRepository.findAllNotDeleted(andId);
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
    public void deleteByUserId(int andId, int userId) {
        dynamicAndMemberRepository.deleteByUserId(andId, userId);
    }

    @Override
    public void update(DynamicAndMemberDTO.Update andMemberDTO) {
        dynamicAndMemberRepository.update(andMemberDTO);
    }
}
