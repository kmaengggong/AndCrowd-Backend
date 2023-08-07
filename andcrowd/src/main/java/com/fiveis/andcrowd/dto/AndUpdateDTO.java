package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.And;
import lombok.*;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class AndUpdateDTO {
    private long andId;
    private String andTitle;
    private String andContent;
    private int andCategoryId;
    private String andHeaderImg;
    private String andImg1;
    private String andImg2;
    private String andImg3;
    private String andImg4;
    private String andImg5;

    public AndUpdateDTO(And and) {
        this.andId = and.getAndId();
        this.andTitle = and.getAndTitle();
        this.andContent = and.getAndContent();
        this.andCategoryId = and.getAndCategoryId();
        this.andHeaderImg = and.getAndHeaderImg();
        this.andImg1 = and.getAndImg1();
        this.andImg2 = and.getAndImg2();
        this.andImg3 = and.getAndImg3();
        this.andImg4 = and.getAndImg4();
        this.andImg5 = and.getAndImg5();
    }
}
