package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdMemberDTO;
import com.fiveis.andcrowd.service.crowd.DynamicCrowdMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crowd/{crowdId}/member")
public class DynamicCrowdMemberController {

    private final DynamicCrowdMemberService dynamicCrowdMemberService;

    @Autowired
    public DynamicCrowdMemberController(DynamicCrowdMemberService dynamicCrowdMemberService) {
        this.dynamicCrowdMemberService = dynamicCrowdMemberService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void saveDynamicCrowdMember(@RequestParam DynamicCrowdMemberDTO.Update update) {
        dynamicCrowdMemberService.save(update);
    }

    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
    public  DynamicCrowdMemberDTO.FindByCrowdMemberId findByCrowdMemberId(
            @PathVariable("crowdId")int crowdId,
            @PathVariable("memberId")int memberId
    ) {
        return dynamicCrowdMemberService.findByCrowdMemberId(crowdId, memberId);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<DynamicCrowdMemberDTO.FindByCrowdMemberId> findAll(@PathVariable("crowdId")int crowdId) {
        return dynamicCrowdMemberService.findAllNotDeleted(crowdId);
    }

    @RequestMapping(value = "/{memberId}/delete", method = RequestMethod.DELETE)
    public void deletedDynamicCrowdMember(
            @PathVariable("crowdId")int crowdId,
            @PathVariable("memberId")int memberId
    ) {
        dynamicCrowdMemberService.deleteById(crowdId, memberId);
    }

    @RequestMapping(value = "/{memberId}/update", method = RequestMethod.PATCH)
    public void updateDynamicCrowdMember(@RequestBody DynamicCrowdMemberDTO.Update update) {
        dynamicCrowdMemberService.update(update);
    }
}
