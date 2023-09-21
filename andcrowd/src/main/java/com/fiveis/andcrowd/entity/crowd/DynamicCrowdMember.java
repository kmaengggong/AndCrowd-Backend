package com.fiveis.andcrowd.entity.crowd;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DynamicCrowdMember { // 공지사항 및 qna 답변 권한은 crowd글 작성자만 가능한 권한 부여

    private int memberId;

    private int crowdId;

    private int userId;

    private boolean isDeleted;

}
