package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdQnaReplyDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicCrowdQnaReplyService {
    void createDynamicCrowdQnaReplyTable(@Param("crowdId") int crowdId);

    List<DynamicCrowdQnaReplyDTO.Find> findAll(@Param("crowdId") int crowdId, @Param("crowdQnaId") int crowdQnaId);

    List<DynamicCrowdQnaReplyDTO.Find> findAllByIsDeletedFalse(@Param("crowdId") int crowdId, @Param("crowdQnaId") int crowdQnaId);

    DynamicCrowdQnaReplyDTO.Find findById(@Param("crowdId") int crowdId, @Param("qnaReplyId") int qnaReplyId);

    void deleteByQnaReplyId(@Param("crowdId") int crowdId, @Param("qnaReplyId") int qnaReplyId);

    void save(DynamicCrowdQnaReplyDTO.Update dynamicCrowdQnaReplyDTOSave);

    void update(DynamicCrowdQnaReplyDTO.Update dynamicCrowdBoardReplyDTOUpdate);
}
