package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.AndDTO;
import com.fiveis.andcrowd.entity.And;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AndService {

    Optional<AndDTO.FindById> findById(int andId);

    List<AndDTO.FindAllByUserId> findAllByUserId(int userId);

    void deleteById(int andId);

    void save(And and);

    public AndDTO.FindById convertToAndFindByIdDTO(And and);

    void update(And and);
}
