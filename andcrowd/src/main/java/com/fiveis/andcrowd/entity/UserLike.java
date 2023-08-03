package com.fiveis.andcrowd.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uLikeId;

    @Column(nullable = false)
    private int projectId;

    @Column(nullable = false)
    private int uLikeKind;
}
