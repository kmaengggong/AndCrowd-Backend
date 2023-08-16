package com.fiveis.andcrowd.service.and;

import com.fiveis.andcrowd.dto.and.DynamicAndApplicantDTO;
import com.fiveis.andcrowd.repository.and.DynamicAndApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicAndApplicantServiceImpl implements DynamicAndApplicantService{

    DynamicAndApplicantRepository dynamicAndApplicantRepository;

    @Autowired
    public DynamicAndApplicantServiceImpl(DynamicAndApplicantRepository dynamicAndApplicantRepository){
        this.dynamicAndApplicantRepository = dynamicAndApplicantRepository;
    }


    @Override
    public List<DynamicAndApplicantDTO.FindById> findAll(int andId) {
        return dynamicAndApplicantRepository.findAll(andId);
    }

    @Override
    public List<DynamicAndApplicantDTO.FindById> findAllNotDeleted(int andId) {
        return dynamicAndApplicantRepository.findAllNotDeleted(andId);
    }

    @Override
    public DynamicAndApplicantDTO.FindById findByAndApplicantId(int andId, int andApplyId) {
        return dynamicAndApplicantRepository.findByAndApplicantId(andId, andApplyId);
    }

    @Override
    public List<DynamicAndApplicantDTO.FindById> findByUserId(int andId, int userId) {
        return dynamicAndApplicantRepository.findByUserId(andId, userId);
    }

    @Override
    public List<DynamicAndApplicantDTO.FindById> findByAndRoleId(int andId, int andRoleId) {
        return dynamicAndApplicantRepository.findByAndRoleId(andId, andRoleId);
    }

    @Override
    public void save(DynamicAndApplicantDTO.Update andApplicantInsertDTO) {
        dynamicAndApplicantRepository.save(andApplicantInsertDTO);
    }

    @Override
    public void update(DynamicAndApplicantDTO.Update andApplicantUpdateDTO) {
        dynamicAndApplicantRepository.update(andApplicantUpdateDTO);
    }

    @Override
    public void deleteByAndApplicantId(int andId, int andApplyId) {
        dynamicAndApplicantRepository.deleteByAndApplicantId(andId, andApplyId);
    }
}
