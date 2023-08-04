package com.fiveis.andcrowd.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder @Entity
@DynamicInsert
@Table(name = "crowd_category")
public class CrowdCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int crowdCategoryId;

    @Column(nullable = false)
    private String crowdCategoryName;
}