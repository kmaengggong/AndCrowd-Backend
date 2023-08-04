package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.entity.And;

import java.util.List;
import java.util.Optional;

public interface AndService {
    Optional<And> findById(int andId);

    List<And> findAllByUserId(int userId);

    void deleteById(int andId);

    void save(And and);

}
