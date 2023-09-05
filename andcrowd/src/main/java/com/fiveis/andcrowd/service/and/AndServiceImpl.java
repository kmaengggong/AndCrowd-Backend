package com.fiveis.andcrowd.service.and;

import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.entity.and.And;
import com.fiveis.andcrowd.repository.and.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AndServiceImpl implements AndService{

    AndJPARepository andJPARepository;
    AndDynamicRepository andDynamicRepository;
    ChatRoomService chatRoomService;
    DynamicAndQnaRepository dynamicAndQnaRepository;
    DynamicAndQnaReplyRepository dynamicAndQnaReplyRepository;
    DynamicAndBoardRepository dynamicAndBoardRepository;


    @Autowired
    public AndServiceImpl(AndJPARepository andJPARepository,
                          AndDynamicRepository andDynamicRepository,
                          DynamicAndQnaRepository dynamicAndQnaRepository,
                          DynamicAndQnaReplyRepository dynamicAndQnaReplyRepository,
                          DynamicAndBoardRepository dynamicAndBoardRepository,
                          ChatRoomService chatRoomService
                          ){
        this.andJPARepository = andJPARepository;
        this.andDynamicRepository = andDynamicRepository;
        this.dynamicAndQnaRepository = dynamicAndQnaRepository;
        this.dynamicAndQnaReplyRepository = dynamicAndQnaReplyRepository;
        this.dynamicAndBoardRepository = dynamicAndBoardRepository;
        this.chatRoomService = chatRoomService;
    }


//    @Override
//    public Optional<AndFindByIdDTO> findById(int andId) {
//        return andJPARepository.findById(andId);
//    }

    @Override
    public Optional<AndDTO.Find> findById(int andId) {
        Optional<And> andOptional = andJPARepository.findById(andId);
        return andOptional.map(AndDTO::convertToAndFindDTO);
    }

    @Override
    public List<AndDTO.FindAllByUserId> findAllByUserId(int userId) {
        return andJPARepository.findAllByUserId(userId);
    }

    @Override
    public List<AndDTO.Find> findAllByIsDeletedFalse() {
        List<And> andList = andJPARepository.findAllByIsDeletedFalse();
        List<AndDTO.Find> findAllNotDeletedList = new ArrayList<>();

        for (And and : andList) {
            AndDTO.Find dto = AndDTO.convertToAndFindDTO(and);
            findAllNotDeletedList.add(dto);
        }


        return findAllNotDeletedList;
    }

    @Override
    public List<AndDTO.Find> findAll() {
        List<And> andList = andJPARepository.findAll();
        List<AndDTO.Find> findList = new ArrayList<>();

        for (And and : andList) {
            AndDTO.Find dto = AndDTO.convertToAndFindDTO(and);
            findList.add(dto);
        }

        return findList;
    }


    @Override
    public void deleteById(int andId) {
        dynamicAndBoardRepository.deleteAll(andId);
        dynamicAndQnaReplyRepository.deleteAll(andId);
        dynamicAndQnaRepository.deleteAll(andId);
        andJPARepository.deleteById(andId);

    }

    @Override
    public void save(And and) {
        And savedAnd = andJPARepository.save(and);
        andDynamicRepository.createDynamicAndQnaTable(savedAnd.getAndId());
        andDynamicRepository.createDynamicAndMemberTable(savedAnd.getAndId());
        andDynamicRepository.createDynamicAndBoardTable(savedAnd.getAndId());
        andDynamicRepository.createDynamicAndRoleTable(savedAnd.getAndId());
        andDynamicRepository.createDynamicAndApplicantTable(savedAnd.getAndId());
        andDynamicRepository.createDynamicAndQnaReplyTable(savedAnd.getAndId());
        chatRoomService.createAndChatroom(savedAnd.getAndId());
    }



    @Override
    public void update(And and) {
        And updatedAnd = andJPARepository.findById(and.getAndId()).get();
        updatedAnd.setAndTitle(and.getAndTitle());
        updatedAnd.setAndContent(and.getAndContent());
        updatedAnd.setAndEndDate(and.getAndEndDate());
        updatedAnd.setAndCategoryId(and.getAndCategoryId());
        updatedAnd.setNeedNumMem(and.getNeedNumMem());
        updatedAnd.setUpdatedAt(LocalDateTime.now());
        andJPARepository.save(updatedAnd);
    }

    @Override
    public void updateStatus(int andId) {
        Optional<And> optionalAnd = andJPARepository.findById(andId);
        if (optionalAnd.isPresent()) {
            And updatedAnd = optionalAnd.get();
            updatedAnd.setAndStatus(2);

            // 변경된 And 객체를 다시 저장
            andJPARepository.save(updatedAnd);
        }
    }

}
