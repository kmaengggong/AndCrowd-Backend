package com.fiveis.andcrowd.Service;

import com.fiveis.andcrowd.dto.AndFindAllByUserIdDTO;
import com.fiveis.andcrowd.dto.AndFindByIdDTO;
import com.fiveis.andcrowd.entity.And;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AndService {
    Optional<AndFindByIdDTO> findById(int andId);

    List<AndFindAllByUserIdDTO> findAllByUserId(int userId);

    void deleteById(int andId);

    void save(And and);

    public AndFindByIdDTO convertToAndFindByIdDTO(And and);

}
