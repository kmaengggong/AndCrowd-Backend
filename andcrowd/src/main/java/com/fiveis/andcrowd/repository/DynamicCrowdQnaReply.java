package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicCrowdQnaReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicCrowdQnaReply {
    void createDynamicCrowdQnaReplyTable(@Param("crowdId") int crowdId);

    List<DynamicCrowdQnaReplyDTO.Find> findAll(@Param("crowdId") int crowdId, @Param("crowdQnaId") int crowdQnaId);

    DynamicCrowdQnaReplyDTO.Find findById(@Param("crowdId") int crowdId, @Param("qnaReplyId") int qnaReplyId);

    void deleteByQnaReplyId(@Param("crowdId") int crowdId, @Param("qnaReplyId") int qnaReplyId);

    void save(DynamicCrowdQnaReplyDTO.Save dynamicCrowdQnaReplyDTOSave);

    void update(DynamicCrowdQnaReplyDTO.Update dynamicCrowdBoardReplyDTOUpdate);
}
