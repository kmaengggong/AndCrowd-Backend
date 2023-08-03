package com.fiveis.andcrowd.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder @Entity
@DynamicInsert
@Table(name = "and_table")
public class And {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int andId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int andCategoryId;

    @Column(nullable = false)
    private int fundingId;

    @Column(nullable = false)
    private String andTitle;

    @Column(nullable = false)
    private String andHeaderImg;

    @Column(nullable = false)
    private String andContent;

    @Column(nullable = false)
    private LocalDateTime andEndDate;

    @Column(nullable = false)
    private int needNumMem;

    private String andImg1;

    private String andImg2;

    private String andImg3;

    private String andImg4;

    private String andImg5;

    private LocalDateTime publishedAt;

    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int andLikeCount;

    @Column(nullable = false)
    private int andViewCount;

    @Column(nullable = false)
    private int andStatus; // 0 : 모집 중, 1 : 심사 중, 2 : 반려, 3: 모집 종료

    @Column(nullable = false)
    private int adMembershipNum;

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