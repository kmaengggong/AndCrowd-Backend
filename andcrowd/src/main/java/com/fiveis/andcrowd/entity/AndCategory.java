package com.fiveis.andcrowd.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder @Entity
public class AndCategory {

    @Id
    @Column(nullable = false)
    private int andCategoryId;

    @Column(nullable = false)
    private int andCategoryName;

    @Column(nullable = false)
    private boolean isDeleted;
}
