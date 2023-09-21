package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicCrowdMemberRepository {
    void save(DynamicCrowdMemberDTO.Update update);

    DynamicCrowdMemberDTO.FindByCrowdMemberId findByCrowdMemberId(@Param("crowdId")int crowdId, @Param("memberId")int memberId);

    List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAll(int crowdId);

    List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAllNotDeletedByUserId(@Param("crowdId")int crowdId, @Param("userId")int userId);

    void deleteById(@Param("crowdId")int crowdId, @Param("memberId")int memberId);

    void deleteByUserId(@Param("crowdId")int crowdId, @Param("userId")int userId);

    void createDynamicCrowdMemberTable(int crowdId);
    void createCrowdMemberTable();
    void dropCrowdMemberTable();
    void insertTestData();

    void update(DynamicCrowdMemberDTO.Update crowdMemberUpdateDTO);
    List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAllNotDeleted(int crowdId);

}
