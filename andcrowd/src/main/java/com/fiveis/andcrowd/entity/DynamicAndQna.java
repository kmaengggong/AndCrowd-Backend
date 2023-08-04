package com.fiveis.andcrowd.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
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

    private LocalDateTime publishedAt;

    private LocalDateTime updatedAt;

    private boolean isDeleted;
}
