package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicCrowdQnaDTO;
import com.fiveis.andcrowd.dto.DynamicCrowdQnaReplyDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DynamicCrowdQnaReplyTest {

    @Autowired
    DynamicCrowdQnaReply dynamicCrowdQnaReply;

    @Test
    @Transactional
    @DisplayName("2번 crowd게시글 생성시 crowd_qna_reply_2 이라는 이름의 테이블이 생성되며, 글이 정상적으로 추가된다.")
    public void createDynamicCrowdQnaReplyTableTest(){
        // given
        int crowdId = 1;
        int crowdQnaId = 1;
        String content = "추가된 댓글";

        // when
        dynamicCrowdQnaReply.createDynamicCrowdQnaReplyTable(crowdId);
        DynamicCrowdQnaReplyDTO.Save newQnaReply = new DynamicCrowdQnaReplyDTO.Save();
        newQnaReply.setCrowdQnaId(crowdQnaId);
        newQnaReply.setQnaReplyContent(content);
        newQnaReply.setCrowdId(crowdId);
        dynamicCrowdQnaReply.save(newQnaReply);

        // then
        List<DynamicCrowdQnaReplyDTO.Find> crowdBoardList = dynamicCrowdQnaReply.findAll(crowdId, crowdQnaId);
        assertThat(crowdBoardList.get(0).getCrowdId()).isEqualTo(crowdId);
    }

    @Test
    @Transactional
    @DisplayName("crowdId 1번글의 1번 qna의 reply 조회시 데이터가 3개일 것이다.")
    public void findAllTest(){
        // given
        int crowdId = 1;
        int crowdQnaId = 1;

        // when
        List<DynamicCrowdQnaReplyDTO.Find> crowdQnaReplyList = dynamicCrowdQnaReply.findAll(crowdId, crowdQnaId);

        // then
        assertThat(crowdQnaReplyList.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    @DisplayName("crowdId 1번글의 crowd_qna_reply 2번째 댓글의 내용은'2번댓글' 일것이다.")
    public void findByIdTest(){

        // given
        int crowdId = 1;
        int qnaReplyId = 2;
        String content = "2번댓글";

        // when
        DynamicCrowdQnaReplyDTO.Find qnaReply = dynamicCrowdQnaReply.findById(crowdId, qnaReplyId);

        // then
        assertThat(qnaReply.getQnaReplyId()).isEqualTo(qnaReplyId);
        assertThat(qnaReply.getQnaReplyContent()).isEqualTo(content);

    }

    @Test
    @Transactional
    @DisplayName("추가된 댓글의 qnaReplyId는 6번일것이며, content가 입력된 값과 일치할 것이다.")
    public void saveTest(){
        // given
        int crowdId = 1;
        int crowdQnaId = 1;
        String content = "추가된댓글";


        // when
        // 빌더로 리팩토링 예정
        DynamicCrowdQnaReplyDTO.Save newQnaReply = new DynamicCrowdQnaReplyDTO.Save();
        newQnaReply.setCrowdQnaId(crowdQnaId);
        newQnaReply.setQnaReplyContent(content);
        newQnaReply.setCrowdId(crowdId);
        dynamicCrowdQnaReply.save(newQnaReply);

        List<DynamicCrowdQnaReplyDTO.Find> crowdQnaReplyList = dynamicCrowdQnaReply.findAll(crowdId, crowdQnaId);

        DynamicCrowdQnaReplyDTO.Find qnaReply = crowdQnaReplyList.get(3);

        // then
        assertThat(content).isEqualTo(qnaReply.getQnaReplyContent());
        assertThat(crowdId).isEqualTo(qnaReply.getCrowdQnaId());
    }

    @Test
    @Transactional
    @DisplayName("2번댓글에 수정된 내용을 입력시 입력된 내용으로 수정된다.")
    public void updateTest(){

        // given
        int crowdId = 1;
        int crowdQnaId = 1;
        int qnaReplyId = 2;
        String content = "수정된본문";


        // when
        // 빌더로 리팩토링 예정
        DynamicCrowdQnaReplyDTO.Update updateQnaReply = new DynamicCrowdQnaReplyDTO.Update();
        updateQnaReply.setCrowdId(crowdId);
        updateQnaReply.setQnaReplyContent(content);
        updateQnaReply.setQnaReplyId(qnaReplyId);
        dynamicCrowdQnaReply.update(updateQnaReply);

        List<DynamicCrowdQnaReplyDTO.Find> crowdQnaReplyList = dynamicCrowdQnaReply.findAll(crowdId, crowdQnaId);

        DynamicCrowdQnaReplyDTO.Find crowdQnaReply = crowdQnaReplyList.get(1);

        // then
        assertThat(content).isEqualTo(crowdQnaReply.getQnaReplyContent());
        assertThat(crowdQnaId).isEqualTo(crowdQnaReply.getCrowdQnaId());
    }

    @Test
    @Transactional
    @DisplayName("2번댓글 삭제시 crowdQna 1번글의 2번 댓글의 isDeleted 는 true일것이다.")
    public void deleteByCrowdBoardIdTest(){

        // given
        int crowdId = 1;
        int qnaReplyId = 2;

        // when
        dynamicCrowdQnaReply.deleteByQnaReplyId(crowdId, qnaReplyId);
        DynamicCrowdQnaReplyDTO.Find qnaReply = dynamicCrowdQnaReply.findById(crowdId, qnaReplyId);

        // then
        assertThat(qnaReply.isDeleted()).isTrue();
    }
}
