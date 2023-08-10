package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.AndDTO;
import com.fiveis.andcrowd.entity.And;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AndService {

    Optional<AndDTO.Find> findById(int andId);

    List<AndDTO.FindAllByUserId> findAllByUserId(int userId);

    List<AndDTO.Find> findAllByIsDeletedFalse();
    List<AndDTO.Find> findAll();

    void deleteById(int andId);

    void save(And and);

    public AndDTO.Find convertToAndFindDTO(And and);

    void update(And and);
}