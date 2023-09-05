package com.fiveis.andcrowd.controller.and;

import com.fiveis.andcrowd.dto.and.DynamicAndApplicantDTO;
import com.fiveis.andcrowd.service.and.DynamicAndApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/and/{andId}/applicant")
public class DynamicAndApplicantController {

    private final DynamicAndApplicantService dynamicAndApplicantService;

    @Autowired
    public DynamicAndApplicantController(DynamicAndApplicantService dynamicAndApplicantService) {
        this.dynamicAndApplicantService = dynamicAndApplicantService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<DynamicAndApplicantDTO.FindById> getList(@PathVariable("andId") int andId){
        return dynamicAndApplicantService.findAllNotDeleted(andId);
    }

    @RequestMapping(value = "/{userId}/all", method = RequestMethod.GET)
    public List<DynamicAndApplicantDTO.FindById> userApplyList(@PathVariable("andId") int andId, @PathVariable("userId") int userId){
        return dynamicAndApplicantService.findByUserId(andId, userId);
    }

    @RequestMapping(value = "/{andRoleId}/all", method = RequestMethod.GET)
    public List<DynamicAndApplicantDTO.FindById> roleApplyList(@PathVariable("andId") int andId, @PathVariable("andRoleId") int andRoleId){
        return dynamicAndApplicantService.findByAndRoleId(andId, andRoleId);
    }

    @RequestMapping(value = "/{andApplyId}", method = RequestMethod.GET)
    public DynamicAndApplicantDTO.FindById apply(@PathVariable("andId") int andId, @PathVariable("andApplyId") int andApplyId){
        return dynamicAndApplicantService.findByAndApplicantId(andId, andApplyId);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createApplicant(@RequestBody DynamicAndApplicantDTO.Update createApplicant){
        dynamicAndApplicantService.save(createApplicant);
    }

    @RequestMapping(value = "/{andApplyId}/update", method = RequestMethod.PATCH)
    public void update(
            @PathVariable("andId") int andId,
            @RequestBody DynamicAndApplicantDTO.Update updatedApply){
        dynamicAndApplicantService.update(updatedApply);
    }

    @RequestMapping(value = "/{andApplyId}/update/admin", method = RequestMethod.PATCH)
    public void updateApplyStatus(
            @PathVariable("andId") int andId,
            @PathVariable("andApplyId") int andApplyId,
            @RequestBody int andApplyStatus){
        dynamicAndApplicantService.updateApplyStatus(andId, andApplyId, andApplyStatus);
    }

    @RequestMapping(value = "/{andApplyId}/delete", method = RequestMethod.DELETE)
    public void deleteApply(@PathVariable("andId") int andId, @PathVariable("andApplyId") int andApplyId){
        dynamicAndApplicantService.deleteByAndApplicantId(andId, andApplyId);
    }

}
