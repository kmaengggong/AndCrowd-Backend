package com.fiveis.andcrowd.service.user;

import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.dto.user.DynamicUserAndDTO;
import com.fiveis.andcrowd.entity.and.And;
import com.fiveis.andcrowd.entity.user.DynamicUserAnd;
import com.fiveis.andcrowd.repository.and.AndJPARepository;
import com.fiveis.andcrowd.repository.user.DynamicUserAndRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicUserAndServiceImpl implements DynamicUserAndService{
    private static DynamicUserAndRepository dynamicUserAndRepository;
    private static AndJPARepository andJPARepository;

    @Autowired
    public DynamicUserAndServiceImpl(DynamicUserAndRepository dynamicUserAndRepository,
                                     AndJPARepository andJPARepository){
        this.dynamicUserAndRepository = dynamicUserAndRepository;
        this.andJPARepository = andJPARepository;
    }

    public List<AndDTO.Find> findAll(String userEmail){
        List<DynamicUserAndDTO.Find> findList = dynamicUserAndRepository.findAll(userEmail);
        List<AndDTO.Find> andList = new ArrayList<>();
        for(DynamicUserAndDTO.Find find : findList){
            andList.add(AndDTO.convertToAndFindDTO(andJPARepository.findById(find.getAndId()).get()));
        }
        return andList;
    }

    public DynamicUserAndDTO.Find findById(String userEmail, int uAndId){
        return dynamicUserAndRepository.findById(userEmail, uAndId);
    }

    public void save(String userEmail, DynamicUserAnd dynamicUserAnd){
        if(dynamicUserAndRepository.findByAndId(userEmail, dynamicUserAnd.getAndId()) != null) return;
        dynamicUserAndRepository.save(userEmail, dynamicUserAnd);
    }

    public void deleteById(String userEmail, int uAndId){
        dynamicUserAndRepository.deleteById(userEmail, uAndId);
    }
}
