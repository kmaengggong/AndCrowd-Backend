package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.AndDTO;
import com.fiveis.andcrowd.entity.And;
import com.fiveis.andcrowd.repository.AndJPARepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AndServiceImpl implements AndService{

    AndJPARepository andJPARepository;

    public AndServiceImpl(AndJPARepository andJPARepository){
        this.andJPARepository = andJPARepository;
    }


//    @Override
//    public Optional<AndFindByIdDTO> findById(int andId) {
//        return andJPARepository.findById(andId);
//    }

    @Override
    public Optional<AndDTO.FindById> findById(int andId) {
        Optional<And> andOptional = andJPARepository.findById(andId);
        return andOptional.map(this::convertToAndFindByIdDTO);
    }

    @Override
    public List<AndDTO.FindAllByUserId> findAllByUserId(int userId) {
        return andJPARepository.findAllByUserId(userId);
    }

    @Override
    public void deleteById(int andId) {
        andJPARepository.deleteById(andId);
    }

    @Override
    public void save(And and) {
        andJPARepository.save(and);
    }

    @Override
    public void update(And and) {
        And updatedAnd = andJPARepository.findById(and.getAndId()).get();
        updatedAnd.setAndTitle(and.getAndTitle());
        updatedAnd.setAndContent(and.getAndContent());
        andJPARepository.save(updatedAnd);
    }


    public AndDTO.FindById convertToAndFindByIdDTO(And and) {
        return AndDTO.FindById.builder()
                .andId(and.getAndId())
                .userId(and.getUserId())
                .andCategoryId(and.getAndCategoryId())
                .fundingId(and.getFundingId())
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
                .adMembershipNum(and.getAdMembershipNum())
                .isDeleted(and.isDeleted())
                .build();
    }

}
