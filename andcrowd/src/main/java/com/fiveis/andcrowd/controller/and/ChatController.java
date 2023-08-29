package com.fiveis.andcrowd.controller.and;

import com.fiveis.andcrowd.controller.and.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate; //특정 Broker로 메세지를 전달
    private final Map<String, Long> map = new HashMap<>();

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/app/message"
    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    private Message receivePublicMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("private-message")
    public Message receivePrivateMessage(@Payload Message message){

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message); // /user/David/private
        return message;
    }
}
