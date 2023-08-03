package com.fiveis.andcrowd.entity;

import lombok.*;

@Getter
@Setter
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
