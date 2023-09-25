package com.fiveis.andcrowd.entity.etc;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatbotMessage {
    private String message;
    private int userId;

}
