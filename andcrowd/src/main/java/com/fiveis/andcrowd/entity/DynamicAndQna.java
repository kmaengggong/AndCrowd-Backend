package com.fiveis.andcrowd.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicAndQna {
    private int andQnaId;

    private int andId;

    private int userId;

    private int andCategoryId;

    private String qnaTitle;

    private String qnaContent;

    private LocalDateTime qnaPublishedAt;

    private LocalDateTime qnaUpdatedAt;

    private boolean isDeleted;
}
