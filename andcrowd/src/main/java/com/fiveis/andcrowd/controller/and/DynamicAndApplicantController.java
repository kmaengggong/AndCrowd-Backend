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


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createApplicant(@RequestBody DynamicAndApplicantDTO.Update createApplicant){
        dynamicAndApplicantService.save(createApplicant);
    }

    @RequestMapping(value = "/{andApplyId}/update", method = RequestMethod.PUT)
    public void update(
            @PathVariable("andId") int andId,
            @RequestBody DynamicAndApplicantDTO.Update updatedApply){
        dynamicAndApplicantService.update(updatedApply);
    }

    @RequestMapping(value = "/{andApplyId}/delete", method = RequestMethod.DELETE)
    public void deleteApply(@PathVariable("andId") int andId, @PathVariable("andApplyId") int andApplyId){
        dynamicAndApplicantService.deleteByAndApplicantId(andId, andApplyId);
    }

}
