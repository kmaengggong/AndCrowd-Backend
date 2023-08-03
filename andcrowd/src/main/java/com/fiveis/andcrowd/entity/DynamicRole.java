package com.fiveis.andcrowd.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicRole {

    private int roleId;

    private int andId;

    private String role;

    private int roleLimit;

    private boolean isDeleted;

}
