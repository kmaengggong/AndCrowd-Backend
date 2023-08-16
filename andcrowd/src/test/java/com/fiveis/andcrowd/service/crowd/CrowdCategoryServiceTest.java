package com.fiveis.andcrowd.service.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdCategoryDTO;
import com.fiveis.andcrowd.service.crowd.CrowdCategoryService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CrowdCategoryServiceTest {
    @Autowired
    CrowdCategoryService crowdCategoryService;

    @Test
    @Transactional
    @DisplayName("전체글 조회시 데이터의 갯수는 4개일것이다.")
    public void finaAllTest(){
        //when
        List<CrowdCategoryDTO.Find> categoryList = crowdCategoryService.findAll();

        //then
        assertThat(categoryList.size()).isEqualTo(4);
    }

    @Test
    @Transactional
    @DisplayName("2번 카테고리 조회시 crowdCategoryName는 제조일것이다.")
    public void findByIdTest(){
        //given
        int crowdCategoryId = 2;
        String crowdCategoryName = "제조";

        //when
        CrowdCategoryDTO.Find crowdCategory = crowdCategoryService.findById(crowdCategoryId);

        //then
        assertThat(crowdCategory.getCrowdCategoryName()).isEqualTo(crowdCategoryName);
    }

}
