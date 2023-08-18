package com.fiveis.andcrowd.entity.crowd;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Getter
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
    private int crowdStatus; // 1:펀딩중, 2:반려, 3:종료, 0:심사중

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

    public void setCrowdContent(String crowdContent) {
        this.crowdContent = crowdContent;
    }

    public void setCrowdGoal(int crowdGoal) {
        this.crowdGoal = crowdGoal;
    }

    public void setCrowdImg1(String crowdImg1) {
        this.crowdImg1 = crowdImg1;
    }

    public void setCrowdImg2(String crowdImg2) {
        this.crowdImg2 = crowdImg2;
    }

    public void setCrowdImg3(String crowdImg3) {
        this.crowdImg3 = crowdImg3;
    }

    public void setCrowdImg4(String crowdImg4) {
        this.crowdImg4 = crowdImg4;
    }

    public void setCrowdImg5(String crowdImg5) {
        this.crowdImg5 = crowdImg5;
    }

    public void setCrowdStatus(int crowdStatus) {
        this.crowdStatus = crowdStatus;
    }

    public void setCrowdTitle(String crowdTitle) {
        this.crowdTitle = crowdTitle;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCrowdEndDate(LocalDateTime crowdEndDate) {
        this.crowdEndDate = crowdEndDate;
    }
}
