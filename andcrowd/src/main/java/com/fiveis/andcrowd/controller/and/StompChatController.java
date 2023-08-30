package com.fiveis.andcrowd.controller.and;

import com.fiveis.andcrowd.controller.and.model.Message;
import com.fiveis.andcrowd.controller.and.model.Status;
import com.fiveis.andcrowd.dto.and.ChatMessageDTO;
import com.fiveis.andcrowd.entity.and.Chat;
import com.fiveis.andcrowd.service.and.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate simpMessagingTemplate; //특정 Broker로 메세지를 전달
    private final Map<String, Long> map = new HashMap<>();
    private final ChatService chatService;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message){
        List<String> liveUser = new ArrayList<>();
        message.setMessage(message.getSenderName() + "님이 채팅방에 참여하였습니다.");

        map.put(message.getSenderName(),message.getRoomId());
        for(Map.Entry<String, Long> entry : map.entrySet()){
            if(entry.getValue().equals(message.getRoomId()) ){
                liveUser.add(entry.getKey());
            }
        }
        message.setUserList(liveUser);
        message.setStatus(Status.JOIN);
        simpMessagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/out")
    public void out(ChatMessageDTO message){
        message.setMessage(message.getSenderName() + "님이 채팅방에 나가셨습니다.");

        List<String> liveUser = new ArrayList<>();
        map.remove(message.getSenderName());
        for(Map.Entry<String, Long> entry : map.entrySet()){
            if(entry.getValue().equals(message.getRoomId()) ){
                liveUser.add(entry.getKey());
            }
        }
        message.setUserList(liveUser);
        message.setStatus(Status.LEAVE);
        simpMessagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }


    @MessageMapping("/message")
    public void sendMessage(@Payload Chat message) {
        // 메시지 저장
        chatService.saveChatMessage(message);

        // 클라이언트에게 메시지 전달
        simpMessagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("private-message")
    public Message receivePrivateMessage(@Payload Message message){

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message); // /user/David/private
        return message;
    }
}
