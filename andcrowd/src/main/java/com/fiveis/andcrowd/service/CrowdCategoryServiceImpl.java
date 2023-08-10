package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.CrowdCategoryDTO;
import com.fiveis.andcrowd.entity.CrowdCategory;
import com.fiveis.andcrowd.exception.NotFoundCrowdCategoryException;
import com.fiveis.andcrowd.repository.CrowdCategoryJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrowdCategoryServiceImpl implements CrowdCategoryService{

    CrowdCategoryJPARepository crowdCategoryJPARepository;

    @Autowired
    public CrowdCategoryServiceImpl(CrowdCategoryJPARepository crowdCategoryJPARepository){
        this.crowdCategoryJPARepository = crowdCategoryJPARepository;
    }

    // 엔터티를 DTO로 변환해서 반환해주는 메서드
    // stream().map() 를 사용하여 각 요소마다 DTO쪽에 선언된 Entity -> DTO 변환 메서드를 적용시켜줌
    // 이후 List 형태로 복원하여 리턴
    @Override
    public List<CrowdCategoryDTO.Find> findAll() {
        return crowdCategoryJPARepository.findAll().stream()
                .map(CrowdCategoryDTO.Find::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public CrowdCategoryDTO.Find findById(int crowdCategoryId) {
        Optional<CrowdCategory> optioanlCrowdCategory = crowdCategoryJPARepository.findById(crowdCategoryId);

        if(optioanlCrowdCategory.isPresent()){
            CrowdCategory crowdCategory = optioanlCrowdCategory.get();
            return CrowdCategoryDTO.Find.fromEntity(crowdCategory);
        }else{
            throw new NotFoundCrowdCategoryException("없는 카테고리 입니다.");
        }
    }
}
