package com.fiveis.andcrowd.Service;

import com.fiveis.andcrowd.entity.And;

import java.util.List;
import java.util.Optional;

public interface AndService {
    Optional<And> findbyId(int andId);

    List<And> findAllbyUserId(int userId);

    void deleteById(int andId);

    void save(And and);

}
