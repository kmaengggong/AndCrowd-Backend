package com.fiveis.andcrowd.service.and;

import com.fiveis.andcrowd.dto.and.ChatRoomDTO;
import com.fiveis.andcrowd.dto.and.DynamicAndMemberDTO;
import com.fiveis.andcrowd.dto.user.DynamicUserAndDTO;
import com.fiveis.andcrowd.dto.user.UserDTO;
import com.fiveis.andcrowd.entity.and.And;
import com.fiveis.andcrowd.entity.and.ChatRoom;
import com.fiveis.andcrowd.entity.user.User;
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

    // 채팅방 ID로 채팅방 조회
    public Optional<ChatRoom> findRoomById(Long id){
        return chatRoomRepository.findByRoomId(id);
    }


    // 사용자의 이름을 받아 해당 사용자가 속한 채팅방 리스트를 조회
    public List<ChatRoom> findByUserNickname(String userNickname) {
        Optional<User> userOptional = userRepository.findByUserNickname(userNickname);
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
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


    public void createAndChatroom(int andId) {
        Optional<And> and = andRepository.findById(andId);
        ChatRoom andChatRoom = new ChatRoom();
        andChatRoom.setName("Chat Room for " + and.get().getAndTitle());
        andChatRoom.setAndId(andId);
        chatRoomRepository.save(andChatRoom);
    }

    public void updateChatRoomName(Long roomId, String newName) {
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Chat room not found with id: " + roomId));

        chatRoom.setName(newName);
        chatRoomRepository.save(chatRoom);
    }

    public List<UserDTO.UserInfo> chatMember(Long roomId) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findByRoomId(roomId);
        int andId = chatRoom.get().getAndId();
        List<DynamicAndMemberDTO.FindByAndMemberId> andMemberList = memberRepository.findAllNotDeleted(andId);
        System.out.println("andMemberList: " + andMemberList);

        List<UserDTO.UserInfo> chatMember = andMemberList.stream()
                .map(dynamicAndMember -> userRepository.findById(dynamicAndMember.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found with userId: " + dynamicAndMember.getUserId())))
                .map(user -> new UserDTO.UserInfo(user.getUserId(), user.getUserKorName(), user.getUserNickname()))
                .distinct() // 중복 제거
                .collect(Collectors.toList());
        System.out.println("chatMember: " + chatMember);

        return chatMember;
    }
}
