package com.fiveis.andcrowd.service;

import com.fiveis.andcrowd.dto.DynamicAndRoleDTO;
import com.fiveis.andcrowd.repository.DynamicAndRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicAndRoleServiceImpl implements DynamicAndRoleService{

    DynamicAndRoleRepository dynamicAndRoleRepository;

    @Autowired
    public DynamicAndRoleServiceImpl(DynamicAndRoleRepository dynamicAndRoleRepository){
        this.dynamicAndRoleRepository = dynamicAndRoleRepository;
    }

    @Override
    public List<DynamicAndRoleDTO.FindById> findAll(int andId) {
        return dynamicAndRoleRepository.findAll(andId);
    }

    @Override
    public DynamicAndRoleDTO.FindById findByAndRoleId(int andId, int andRoleId) {
        return dynamicAndRoleRepository.findByAndRoleId(andId, andRoleId);
    }

    @Override
    public void save(DynamicAndRoleDTO.Update andRoleInsertDTO) {
        dynamicAndRoleRepository.save(andRoleInsertDTO);
    }

    @Override
    public void update(DynamicAndRoleDTO.Update andRoleUpdateDTO) {
        dynamicAndRoleRepository.update(andRoleUpdateDTO);
    }

    @Override
    public void deleteByAndRoleId(int andId, int andRoleId) {
        dynamicAndRoleRepository.deleteByAndRoleId(andId, andRoleId);
    }
}
