package com.fiveis.andcrowd.entity.and;

import com.fiveis.andcrowd.controller.and.model.Status;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roomId;
    private String senderName;

    @Column(nullable = true)
    private String receiverName;

    private LocalDateTime publishedAt;
    private String message;
    private Status status;

}