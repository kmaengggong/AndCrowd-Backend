package com.fiveis.andcrowd.dto.and;

import com.fiveis.andcrowd.controller.and.model.Status;
import com.fiveis.andcrowd.entity.and.Chat;
import com.fiveis.andcrowd.entity.and.ChatRoom;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {

    private Long roomId;
    private String senderName;
    private String receiverName;
    private String message;

    private LocalDateTime publishedAt;
    private List<String> userList;
    private Status status;

    public Chat toChat(ChatRoom chatRoom){
        return Chat.builder().message(message)
                .senderName(senderName)
                .receiverName(receiverName)
                .publishedAt(publishedAt)
                .roomId(roomId)
                .status(status)
                .build();

    }

}
