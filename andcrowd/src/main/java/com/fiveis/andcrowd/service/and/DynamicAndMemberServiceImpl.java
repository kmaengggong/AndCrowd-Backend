package com.fiveis.andcrowd.service.and;

import com.fiveis.andcrowd.dto.and.DynamicAndMemberDTO;
import com.fiveis.andcrowd.dto.and.DynamicAndQnaDTO;
import com.fiveis.andcrowd.dto.and.DynamicAndQnaReplyDTO;
import com.fiveis.andcrowd.repository.and.DynamicAndApplicantRepository;
import com.fiveis.andcrowd.repository.and.DynamicAndMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicAndMemberServiceImpl implements DynamicAndMemberService {

    private final DynamicAndMemberRepository dynamicAndMemberRepository;
    private final DynamicAndApplicantRepository dynamicAndApplicantRepository;
    private final DynamicAndApplicantService dynamicAndApplicantService;

    @Autowired
    public DynamicAndMemberServiceImpl(DynamicAndMemberRepository dynamicAndMemberRepository,
                                       DynamicAndApplicantRepository dynamicAndApplicantRepository,
                                       DynamicAndApplicantService dynamicAndApplicantService) {
        this.dynamicAndMemberRepository = dynamicAndMemberRepository;
        this.dynamicAndApplicantRepository = dynamicAndApplicantRepository;
        this.dynamicAndApplicantService = dynamicAndApplicantService;
    }

    @Override
    public void save(DynamicAndMemberDTO.Update dynamicAndMemberDTOUpdate) {
        int andId = dynamicAndMemberDTOUpdate.getAndId();
        int userId = dynamicAndMemberDTOUpdate.getUserId();

        List<DynamicAndMemberDTO.FindByAndMemberId> findAllNotDeletedByUserId = dynamicAndMemberRepository.findAllNotDeletedByUserId(andId, userId);

        if (findAllNotDeletedByUserId.isEmpty()) {
            dynamicAndMemberRepository.save(dynamicAndMemberDTOUpdate);
        }
    }

    @Override
    public List<DynamicAndMemberDTO.FindByAndMemberId> findAllNotDeleted(int andId) {
        return dynamicAndMemberRepository.findAllNotDeleted(andId);
    }

    @Override
    public List<DynamicAndMemberDTO.FindByAndMemberId> findAllNotDeletedByUserId(int andId, int userId) {
        return dynamicAndMemberRepository.findAllNotDeletedByUserId(andId, userId);
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
        int andApplyId = dynamicAndMemberRepository.findByAndMemberId(andId, memberId).getAndApplyId();
        int andApplyStatus = 3;
        System.out.println("memberId: "+memberId);
        System.out.println("andId: "+andId);
        System.out.println("andApplyId: "+andApplyId);
        System.out.println("andApplyStatus: "+andApplyStatus);
        dynamicAndApplicantService.updateApplyStatus(andId, andApplyId, andApplyStatus);
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
