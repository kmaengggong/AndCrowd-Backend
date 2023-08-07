package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicCrowdBoardDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DynamicCrowdBoardTest {

    @Autowired
    DynamicCrowdBoard dynamicCrowdBoard;



    @Test
    @Transactional
    @DisplayName("2번 crowd게시글 생성시 crowd_board_2 이라는 이름의 테이블이 생성된다")
    public void createDynamicCrowdBoardTableTest(){
        // given
        int crowdId = 2;

        // when
        dynamicCrowdBoard.createDynamicCrowdBoardTable(crowdId);

        // then
        System.out.println();
    }


    @Test
    @Transactional
    @DisplayName("crowdId 1번글의 전체글 조회시 6개일 것이다.")
    public void findAllTest(){

        // given
        int crowdId = 1;

        // when
        List<DynamicCrowdBoardDTO.Find> crowdBoardList = dynamicCrowdBoard.findAll(crowdId);

        // then
        assertThat(crowdBoardList.size()).isEqualTo(6);
    }

    @Test
    @Transactional
    @DisplayName("crowdId 1번글의 2번째 글의 제목은 '2번글제목', 본문은 '2번글본문' 일것이다.")
    public void findByIdTest(){

        // given
        int crowdId = 1;
        int crowdBoardId = 2;
        String title = "2번글제목";
        String content = "2번글본문";

        // when
        Map<String, Integer> params = new HashMap<>();
        params.put("crowdId", crowdId);
        params.put("crowdBoardId", crowdBoardId);
        DynamicCrowdBoardDTO.Find crowdBoard = dynamicCrowdBoard.findById(params);

        // then
        assertThat(crowdBoard.getCrowdId()).isEqualTo(crowdId);
        assertThat(crowdBoard.getCrowdBoardId()).isEqualTo(crowdBoardId);
        assertThat(crowdBoard.getCrowdBoardTitle()).isEqualTo(title);
        assertThat(crowdBoard.getCrowdBoardContent()).isEqualTo(content);

    }

    @Test
    @Transactional
    @DisplayName("추가된 글의 crowdBoardId는 7번일것이다. 전체 데이터의 첫번째 인덱스의 각요소들은 입력한 데이터와 일치할 것이다.")
    public void saveTest(){
        // given
        int crowdId = 1;
        int tag = 1;
        String title = "추가된 제목";
        String content = "추가된 본문";
        String img = "추가된 이미지";


        // when
        // 빌더로 리팩토링 예정
        DynamicCrowdBoardDTO.Save newBoard = new DynamicCrowdBoardDTO.Save();
        newBoard.setCrowdId(crowdId);
        newBoard.setCrowdBoardTag(tag);
        newBoard.setCrowdBoardTitle(title);
        newBoard.setCrowdBoardContent(content);
        newBoard.setCrowdImg(img);
        dynamicCrowdBoard.save(newBoard);

        List<DynamicCrowdBoardDTO.Find> crowdBoardList = dynamicCrowdBoard.findAll(crowdId);

        DynamicCrowdBoardDTO.Find crowdBoard = crowdBoardList.get(0);

        // then
        assertThat(title).isEqualTo(crowdBoard.getCrowdBoardTitle());
        assertThat(content).isEqualTo(crowdBoard.getCrowdBoardContent());
        assertThat(img).isEqualTo(crowdBoard.getCrowdImg());
        assertThat(crowdId).isEqualTo(crowdBoard.getCrowdId());
        assertThat(tag).isEqualTo(crowdBoard.getCrowdBoardTag());
    }



}
