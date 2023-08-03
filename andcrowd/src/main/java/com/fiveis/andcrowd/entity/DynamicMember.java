package com.fiveis.andcrowd.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicMember {

    private int memberId;

    private int andId;

    private int userId;

    private int andCategoryId;

    private boolean isDeleted;

}
