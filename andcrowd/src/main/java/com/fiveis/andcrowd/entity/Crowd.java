package com.fiveis.andcrowd.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
public class Crowd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int crowdId;

    @Column(nullable = false)
    private int adId;

    @Column(nullable = false)
    private int andId;

    @Column(nullable = false)
    private int crowdCategoryId;

    @Column(nullable = false)
    private String crowdContent;

    @Column(nullable = false)
    private LocalDateTime crowdEndDate;

    @Column(nullable = false)
    private int crowdGoal;

    private String crowdImg1;
    private String crowdImg2;
    private String crowdImg3;
    private String crowdImg4;
    private String crowdImg5;

    @ColumnDefault("0")
    private int crowdStatus;

    @Column(nullable = false)
    private String crowdTitle;

    @Column(nullable = false)
    private String headerImg;

    @ColumnDefault("true")
    private boolean isDeleted;

    @ColumnDefault("0")
    private int likeSum;

    @Column(nullable = false)
    private LocalDateTime publishedAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int userId;

    @ColumnDefault("0")
    private int viewCount;

    @PrePersist
    public void setDefaultValue(){
        this.publishedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }

}
