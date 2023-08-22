package com.fiveis.andcrowd.controller.and;

import com.fiveis.andcrowd.dto.and.DynamicAndMemberDTO;
import com.fiveis.andcrowd.service.and.DynamicAndMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dynamicAndMember/{andId}")
public class DynamicAndMemberController {

    private final DynamicAndMemberService dynamicAndMemberService;

    @Autowired
    public DynamicAndMemberController(DynamicAndMemberService dynamicAndMemberService) {
        this.dynamicAndMemberService = dynamicAndMemberService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void saveDynamicAndMember(@RequestBody DynamicAndMemberDTO.Update dynamicAndMemberDTOUpdate) {
        dynamicAndMemberService.save(dynamicAndMemberDTOUpdate);
    }

    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
    public DynamicAndMemberDTO.FindByAndMemberId getDynamicAndMemberById(
            @PathVariable("andId") int andId,
            @PathVariable("memberId") int memberId
    ) {
        return dynamicAndMemberService.findByAndMemberId(andId, memberId);
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public List<DynamicAndMemberDTO.FindByAndMemberId> getAllDynamicAndMembers(
            @PathVariable("andId") int andId
    ) {
        return dynamicAndMemberService.findAll(andId);
    }

    @RequestMapping(value = "/{memberId}/delete", method = RequestMethod.DELETE)
    public void deleteDynamicAndMember(
            @PathVariable("andId") int andId,
            @PathVariable("memberId") int memberId
    ) {
        dynamicAndMemberService.deleteById(andId, memberId);
    }

    @RequestMapping("/{memberId}/update")
    public void updateDynamicAndMember(@RequestBody DynamicAndMemberDTO.Update dynamicAndMemberDTOUpdate) {
        dynamicAndMemberService.update(dynamicAndMemberDTOUpdate);
    }


}
