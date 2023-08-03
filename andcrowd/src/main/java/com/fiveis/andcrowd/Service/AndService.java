package com.fiveis.andcrowd.Service;

import com.fiveis.andcrowd.entity.And;

import java.util.List;

public interface AndService {
    And findbyAndId(int andId);

    List<And> findAllbyUserId(int userId);

    void deleteByAndId(int andId);

    void save(And and);

}
