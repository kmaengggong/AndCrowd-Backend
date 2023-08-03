package com.fiveis.andcrowd.Service;

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
    public Optional<And> findbyId(int andId) {
        return andJPARepository.findById(andId);
    }

    @Override
    public List<And> findAllbyUserId(int userId) {
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
