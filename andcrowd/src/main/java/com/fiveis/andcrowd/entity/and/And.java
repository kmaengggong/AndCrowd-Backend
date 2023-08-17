package com.fiveis.andcrowd.entity.and;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder @Entity
@DynamicInsert
@Table(name = "and_table")
@SQLDelete(sql = "UPDATE and_table SET is_deleted = true WHERE and_id = ?")
public class And {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int andId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int andCategoryId;

    @Column(nullable = true)
    private int crowdId;

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

    @Column(nullable = true, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime publishedAt;

    @Column(nullable = true, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @ColumnDefault("0")
    private int andLikeCount;

    @ColumnDefault("0")
    private int andViewCount;

    @ColumnDefault("1")
    private int andStatus; // 0 : 모집 중, 1 : 심사 중, 2 : 반려, 3: 모집 종료

    @Column(nullable = true)
    private int adId;

    @ColumnDefault("false")
    private boolean isDeleted;

    @PrePersist
    public void setDefaultValue(){
        this.publishedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isDeleted = false;
        this.andLikeCount = 0;
        this.andViewCount = 0;
        this.andStatus = 1; // 처음 작성시 자동으로 심사 중인 1로 표기
    }

    @PreUpdate
    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }

}