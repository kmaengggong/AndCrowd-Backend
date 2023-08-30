package com.fiveis.andcrowd.service.and;

import com.fiveis.andcrowd.dto.and.ChatMessageDTO;
import com.fiveis.andcrowd.dto.and.Response;
import com.fiveis.andcrowd.entity.and.Chat;
import com.fiveis.andcrowd.entity.and.ChatRoom;
import com.fiveis.andcrowd.exception.AppException;
import com.fiveis.andcrowd.exception.ErrorCode;
import com.fiveis.andcrowd.repository.and.ChatRepository;
import com.fiveis.andcrowd.repository.and.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public Response addChat(ChatMessageDTO chatMessageDTO){
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(chatMessageDTO.getRoomId()).orElseThrow(()->new AppException(ErrorCode.DB_ERROR,""));
        Chat chater = chatRepository.save(chatMessageDTO.toChat(chatRoom));
        return Response.success(chater);
    }


    private ChatRoom findOrCreateChatRoom(Long roomId) {
        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findByRoomId(roomId);

        if (existingChatRoom.isPresent()) {
            return existingChatRoom.get();
        } else {
            // ChatRoom이 없으면 새로 생성
            ChatRoom newChatRoom = ChatRoom.builder()
                    .roomId(roomId)
                    .build();
            return chatRoomRepository.save(newChatRoom);
        }
    }


    public List<ChatMessageDTO> listChat(Long roomId){
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId).orElseThrow(()->new AppException(ErrorCode.DB_ERROR,""));
        List<Chat> chats = chatRepository.findByRoomId(roomId);
        List<ChatMessageDTO> list = new ArrayList<>();

        for(Chat chat : chats){
            ChatMessageDTO chatMessageDTO = ChatMessageDTO.builder()
                    .message(chat.getMessage())
                    .senderName(chat.getSenderName())
                    .receiverName(chat.getReceiverName())
                    .publishedAt(chat.getPublishedAt())
                    .status(chat.getStatus())
                    .build();
            list.add(chatMessageDTO);
        }
        return list;
    }

    public void saveChatMessage(Chat chat){
        chat.setPublishedAt(LocalDateTime.now());
        chatRepository.save(chat);
    }
}