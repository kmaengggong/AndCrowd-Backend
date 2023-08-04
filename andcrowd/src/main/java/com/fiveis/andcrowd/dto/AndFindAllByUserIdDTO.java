package com.fiveis.andcrowd.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AndFindAllByUserIdDTO {

    private int andId;

    private int userId;

    private String andTitle;

    private String andHeaderImg;

    private LocalDateTime publishedAt;

    private LocalDateTime updatedAt;

    private int andStatus;

    private int adMembershipNum;

    private boolean isDeleted;

}
