package com.fiveis.andcrowd.repository.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdQnaReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicCrowdQnaReplyRepository {
    void createDynamicCrowdQnaReplyTable(@Param("crowdId") int crowdId);

    List<DynamicCrowdQnaReplyDTO.Find> findAll(@Param("crowdId") int crowdId, @Param("crowdQnaId") int crowdQnaId);

    DynamicCrowdQnaReplyDTO.Find findById(@Param("crowdId") int crowdId, @Param("qnaReplyId") int qnaReplyId);

    void deleteByQnaReplyId(@Param("crowdId") int crowdId, @Param("qnaReplyId") int qnaReplyId);

    void save(DynamicCrowdQnaReplyDTO.Save dynamicCrowdQnaReplyDTOSave);

    void update(DynamicCrowdQnaReplyDTO.Update dynamicCrowdBoardReplyDTOUpdate);

    // qna글 삭제시 연관된 reply의 is_delete를 전부 true로 바꾸는 기능
    void deleteAllByQnaId(@Param("crowdId") int crowdId, @Param("crowdQnaId") int crowdQnaId);

    // crowd글 삭제시 연관된 reply의 is_deleted를 전부 true로 바꾸는 기능
    void deleteAll(@Param("crowdId") int crowdId);
}
