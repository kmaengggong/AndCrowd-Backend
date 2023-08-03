package com.fiveis.andcrowd.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicUserAnd {

    private int uAndId;
    
    private int andId;

    private boolean isDeleted;

}
