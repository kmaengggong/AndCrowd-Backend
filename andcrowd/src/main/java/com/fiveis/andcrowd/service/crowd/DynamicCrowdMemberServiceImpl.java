package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdMemberDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.repository.crowd.DynamicCrowdMemberRepository;
import com.fiveis.andcrowd.repository.user.UserJPARepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DynamicCrowdMemberServiceImpl implements DynamicCrowdMemberService {

    private final DynamicCrowdMemberRepository dynamicCrowdMemberRepository;
    private final UserJPARepository userJPARepository;

    @Override
    public void save(DynamicCrowdMemberDTO.Update update) {
        int crowdId = update.getCrowdId();
        int userId = update.getUserId();

        List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAllNotDeletedByUserId = dynamicCrowdMemberRepository.findAllNotDeletedByUserId(crowdId, userId);

        if(findAllNotDeletedByUserId.isEmpty()) {
            dynamicCrowdMemberRepository.save(update);
        }
    }

    @Override
    public void createDynamicCrowdMemberTable(int crowdId) {
        dynamicCrowdMemberRepository.createDynamicCrowdMemberTable(crowdId);
    }

    @Override
    public List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAllNotDeleted(int crowdId) {
        return dynamicCrowdMemberRepository.findAllNotDeleted(crowdId);
    }

    @Override
    public List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAllNotDeletedByUserId(int crowdId, int userId) {
        return dynamicCrowdMemberRepository.findAllNotDeletedByUserId(crowdId, userId);
    }

    @Override
    public DynamicCrowdMemberDTO.FindByCrowdMemberId findByCrowdMemberId(int crowdId, int memberId) {
        return dynamicCrowdMemberRepository.findByCrowdMemberId(crowdId, memberId);
    }

    @Override
    public List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAll(int crowdId) {
        return dynamicCrowdMemberRepository.findAll(crowdId);
    }

    @Override
    public void deleteById(int crowdId, int memberId) {
        dynamicCrowdMemberRepository.deleteById(crowdId, memberId);
    }

    @Override
    public void deleteByUserId(int crowdId, int userId) {
        dynamicCrowdMemberRepository.deleteByUserId(crowdId, userId);
    }

    @Override
    public void update(DynamicCrowdMemberDTO.Update crowdMemberUpdateDTO) {
        dynamicCrowdMemberRepository.update(crowdMemberUpdateDTO);
    }

    @Override
    public List<UserDTO.UserInfo> findAllIsDeletedFalse(int crowdId) {
        List<DynamicCrowdMemberDTO.FindByCrowdMemberId> crowdMemberList = dynamicCrowdMemberRepository.findAllNotDeleted(crowdId);

        List<UserDTO.UserInfo> memberList = crowdMemberList.stream()
                .map(crowdMember -> {
                    User user = userJPARepository.findById(crowdMember.getUserId())
                            .orElseThrow(() -> new EntityNotFoundException("User not found with userId: " + crowdMember.getUserId()));

                    UserDTO.UserInfo userInfo = new UserDTO.UserInfo();
                    userInfo.setUserId(user.getUserId());
                    userInfo.setUserNickname(user.getUserNickname());
                    return userInfo;
                })
                .collect(Collectors.toList());

        System.out.println("member: " + memberList);
        return memberList;
    }
}
