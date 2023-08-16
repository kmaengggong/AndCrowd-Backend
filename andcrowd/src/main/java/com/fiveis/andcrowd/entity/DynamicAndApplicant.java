package com.fiveis.andcrowd.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicAndApplicant {

    private int applyId;

    private int andId;

    private int userId;

    private int andRoleId;

    private String applyContent;

    private int andApplyStatus;

    private boolean isDeleted;
}
