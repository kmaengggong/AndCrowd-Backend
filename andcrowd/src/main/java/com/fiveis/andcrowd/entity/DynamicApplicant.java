package com.fiveis.andcrowd.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicApplicant {

    private int applyId;

    private int andId;

    private int userId;

    private int roleId;

    private int andCategoryId;

    private String applyContent;

    private boolean isDeleted;
}
