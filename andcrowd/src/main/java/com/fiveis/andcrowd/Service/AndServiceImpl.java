package com.fiveis.andcrowd.Service;

import com.fiveis.andcrowd.entity.And;

import java.util.List;

public class AndServiceImpl implements AndService{

    AndJPARepository andJPARepository;

    public AndServiceImpl(AndJPARepository andJPARepository){
        this.andJPARepository = andJPARepository;
    }


    @Override
    public And findbyAndId(int andId) {
        return andJPARepository.findByAndId(andId);
    }

    @Override
    public List<And> findAllbyUserId(int userId) {
        return andJPARepository.findAllByUserId(userId);
    }

    @Override
    public void deleteByAndId(int andId) {
        andJPARepository.deleteByAndId(andId);
    }

    @Override
    public void save(And and) {
        andJPARepository.save(and);
    }
}
