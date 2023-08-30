package com.fiveis.andcrowd.service.and;

import com.fiveis.andcrowd.dto.and.ChatRoomDTO;
import com.fiveis.andcrowd.dto.and.DynamicAndMemberDTO;
import com.fiveis.andcrowd.dto.user.DynamicUserAndDTO;
import com.fiveis.andcrowd.entity.and.And;
import com.fiveis.andcrowd.entity.and.ChatRoom;
import com.fiveis.andcrowd.entity.user.User;
import com.fiveis.andcrowd.exception.AppException;
import com.fiveis.andcrowd.exception.ErrorCode;
import com.fiveis.andcrowd.repository.and.AndJPARepository;
import com.fiveis.andcrowd.repository.and.ChatRoomRepository;
import com.fiveis.andcrowd.repository.and.DynamicAndMemberRepository;
import com.fiveis.andcrowd.repository.user.DynamicUserAndRepository;
import com.fiveis.andcrowd.repository.user.UserJPARepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final AndJPARepository andRepository;
    private final DynamicAndMemberRepository memberRepository;
    private final UserJPARepository userRepository;
    private final DynamicUserAndRepository userAndRepository;

    // ChatRoomDTO 형태로 변환
    public List<ChatRoomDTO> findAllRooms(){
        List<ChatRoom> result = chatRoomRepository.findAll();
        List<ChatRoomDTO> list =  ChatRoomDTO.createList(result);
        return list;
    }

    // 사용자의 이름을 받아 해당 사용자가 속한 채팅방 리스트를 조회
    public List<ChatRoom> findByUserNickname(String userNickname){
        User user = userRepository.findByUserNickname(userNickname).orElseThrow(()->new AppException(ErrorCode.USERID_NOT_FOUND,ErrorCode.USERID_NOT_FOUND.getMessage()));
        String userEmail = user.getUserEmail();
        List<DynamicUserAndDTO.Find> userAndList= userAndRepository.findAll(userEmail);
        // andId와 그에 일치하는 ChatRoom를 반복해서 얻어와 list 얻기
        List<Integer> andIds = userAndList.stream()
                .map(DynamicUserAndDTO.Find::getAndId)
                .toList();

        List<ChatRoom> chatRooms = new ArrayList<>();

        for (Integer andId : andIds) {
            ChatRoom chatRoom = chatRoomRepository.findByAndId(andId);
                chatRooms.add(chatRoom);

        }
        System.out.println("chatRooms: " + chatRooms);

        return chatRooms;
    }

    public ChatRoom findByAndId(Integer andId){
        return chatRoomRepository.findByAndId(andId);
    }

    // 채팅방 ID로 채팅방 조회
    public ChatRoom findRoomById(Long id){
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(id).orElseThrow(()->new AppException(ErrorCode.DB_ERROR,""));
        return chatRoom;
    }


    public void createAndChatroom(int andId) {
        Optional<And> and = andRepository.findById(andId);
        ChatRoom andChatRoom = new ChatRoom();
        andChatRoom.setName("Chat Room for " + and.get().getAndTitle());
        chatRoomRepository.save(andChatRoom);
    }

    public List<User> chatMember(Long roomId) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findByRoomId(roomId);
        int andId = chatRoom.get().getAndId();
        List<DynamicAndMemberDTO.FindByAndMemberId> andMemberList = memberRepository.findAll(andId);

        List<User> chatMember = andMemberList.stream()
                .map(dynamicAndMember -> userRepository.findById(dynamicAndMember.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found with userId: " + dynamicAndMember.getUserId())))
                .collect(Collectors.toList());

        return chatMember;
    }
}
