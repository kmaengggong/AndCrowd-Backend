package com.fiveis.andcrowd.repository;

import org.apache.ibatis.annotations.Mapper;
import com.fiveis.andcrowd.entity.And;

import java.util.List;

@Mapper
public interface AndRepository {
    List<And> findAllAnd();

    And findById(int andId);

    void save(And and);

    void deleteById(int andId);

    void update(And and);
}
