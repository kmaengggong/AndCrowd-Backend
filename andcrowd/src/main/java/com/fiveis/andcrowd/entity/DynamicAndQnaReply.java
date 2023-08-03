package com.fiveis.andcrowd.entity;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicAndQnaReply {

    private int andReplyId;

    private int andQnaId;

    private int userId;

    private int andCategoryId;

    private String andReplyContent;

    private LocalDateTime andReplyPublishedAt;

    private Boolean isDeleted;

}
