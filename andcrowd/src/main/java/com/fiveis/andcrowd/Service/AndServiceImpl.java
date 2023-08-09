package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.entity.And;
import com.fiveis.andcrowd.repository.AndJPARepository;

import java.util.List;
import java.util.Optional;

public class AndServiceImpl implements AndService{

    AndJPARepository andJPARepository;

    public AndServiceImpl(AndJPARepository andJPARepository){
        this.andJPARepository = andJPARepository;
    }


    @Override
    public Optional<And> findById(int andId) {
        return andJPARepository.findById(andId);
    }

    @Override
    public List<And> findAllByUserId(int userId) {
        return andJPARepository.findAllByUserId(userId);
    }

    @Override
    public void deleteById(int andId) {
        andJPARepository.deleteById(andId);
    }

    @Override
    public void save(And and) {
        andJPARepository.save(and);
    }
}
