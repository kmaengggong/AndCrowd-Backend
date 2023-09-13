package com.fiveis.andcrowd.service.and;

import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.entity.and.And;
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
    void update(And and);

    void updateStatus(int andId);
}