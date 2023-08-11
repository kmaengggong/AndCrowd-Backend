package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.AndDTO;
import com.fiveis.andcrowd.entity.And;
import com.fiveis.andcrowd.repository.AndDynamicRepository;
import com.fiveis.andcrowd.repository.AndJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AndServiceImpl implements AndService{

    AndJPARepository andJPARepository;
    AndDynamicRepository andDynamicRepository;

    @Autowired
    public AndServiceImpl(AndJPARepository andJPARepository, AndDynamicRepository andDynamicRepository){
        this.andJPARepository = andJPARepository;
        this.andDynamicRepository = andDynamicRepository;
    }


//    @Override
//    public Optional<AndFindByIdDTO> findById(int andId) {
//        return andJPARepository.findById(andId);
//    }

    @Override
    public Optional<AndDTO.Find> findById(int andId) {
        Optional<And> andOptional = andJPARepository.findById(andId);
        return andOptional.map(this::convertToAndFindDTO);
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
            AndDTO.Find dto = convertToAndFindDTO(and);
            findAllNotDeletedList.add(dto);
        }


        return findAllNotDeletedList;
    }

    @Override
    public List<AndDTO.Find> findAll() {
        List<And> andList = andJPARepository.findAll();
        List<AndDTO.Find> findList = new ArrayList<>();

        for (And and : andList) {
            AndDTO.Find dto = convertToAndFindDTO(and);
            findList.add(dto);
        }

        return findList;
    }


    @Override
    public void deleteById(int andId) {
        andJPARepository.deleteById(andId);
    }

    @Override
    public void save(And and) {
        And savedAnd = andJPARepository.save(and);
        andDynamicRepository.createDynamicAndQnaTable(savedAnd.getAndId());
    }

    @Override
    public void update(And and) {
        And updatedAnd = andJPARepository.findById(and.getAndId()).get();
        updatedAnd.setAndTitle(and.getAndTitle());
        updatedAnd.setAndContent(and.getAndContent());
        andJPARepository.save(updatedAnd);
    }


    public AndDTO.Find convertToAndFindDTO(And and) {
        return AndDTO.Find.builder()
                .andId(and.getAndId())
                .userId(and.getUserId())
                .andCategoryId(and.getAndCategoryId())
                .crowdId(and.getCrowdId())
                .andTitle(and.getAndTitle())
                .andHeaderImg(and.getAndHeaderImg())
                .andContent(and.getAndContent())
                .andEndDate(and.getAndEndDate())
                .needNumMem(and.getNeedNumMem())
                .andImg1(and.getAndImg1())
                .andImg2(and.getAndImg2())
                .andImg3(and.getAndImg3())
                .andImg4(and.getAndImg4())
                .andImg5(and.getAndImg5())
                .publishedAt(and.getPublishedAt())
                .updatedAt(and.getUpdatedAt())
                .andLikeCount(and.getAndLikeCount())
                .andViewCount(and.getAndViewCount())
                .andStatus(and.getAndStatus())
                .adId(and.getAdId())
                .isDeleted(and.isDeleted())
                .build();
    }

}