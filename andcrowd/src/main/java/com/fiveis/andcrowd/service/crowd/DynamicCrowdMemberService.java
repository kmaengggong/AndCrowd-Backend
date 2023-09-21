package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdMemberDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicCrowdMemberService {

    void save(DynamicCrowdMemberDTO.Update update);

    void createDynamicCrowdMemberTable(int crowdId);

    List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAllNotDeleted(int crowdId);

    List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAllNotDeletedByUserId(int crowdId, int userId);

    DynamicCrowdMemberDTO.FindByCrowdMemberId findByCrowdMemberId(int crowdId, int memberId);

    List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAll(int crowdId);

    void deleteById(int crowdId, int memberId);

    void deleteByUserId(int crowdId, int userId);

    void update(DynamicCrowdMemberDTO.Update crowdMemberUpdateDTO);

    List<UserDTO.UserInfo> findAllIsDeletedFalse(int crowdId);
}
