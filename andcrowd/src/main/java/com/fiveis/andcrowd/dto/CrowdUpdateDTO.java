package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.Crowd;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CrowdUpdateDTO {
    // UpdateRequest시 필요한 멤버변수 : 크라우드Id, 카테고리Id, 본문, 마감일, 사진 1-5, 제목, 대표사진
    private int crowdId;
    private int crowdCategoryId;
    private String crowdContent;
    private LocalDateTime crowdEndDate;
    private String crowdImg1;
    private String crowdImg2;
    private String crowdImg3;
    private String crowdImg4;
    private String crowdImg5;
    private String crowdTitle;
    private String headerImg;
    
    public CrowdUpdateDTO(Crowd crowd){ // UpdateRequest 생성자
        this.crowdId = crowd.getCrowdId();
        this.crowdCategoryId = crowd.getCrowdCategoryId();
        this.crowdContent = crowd.getCrowdContent();
        this.crowdEndDate = crowd.getCrowdEndDate();
        this.crowdImg1 = crowd.getCrowdImg1();
        this.crowdImg2 = crowd.getCrowdImg2();
        this.crowdImg3 = crowd.getCrowdImg3();
        this.crowdImg4 = crowd.getCrowdImg4();
        this.crowdImg5 = crowd.getCrowdImg5();
        this.crowdTitle = crowd.getCrowdTitle();
        this.headerImg = crowd.getHeaderImg();
    }
}
