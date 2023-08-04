package com.fiveis.andcrowd.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AndUpdateDTO {
    private int andId;

    private int userId;

    private int andCategoryId;

    private int fundingId;

    private String andTitle;

    private String andHeaderImg;

    private String andContent;

    private LocalDateTime andEndDate;

    private int needNumMem;

    private String andImg1;

    private String andImg2;

    private String andImg3;

    private String andImg4;

    private String andImg5;

    private LocalDateTime publishedAt;

    private LocalDateTime updatedAt;

    private int adMembershipNum;

    private boolean isDeleted;

}
