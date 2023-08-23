package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdRewardDTO;
import com.fiveis.andcrowd.service.crowd.DynamicCrowdRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{crowdId}/reward")
public class DynamicCrowdRewardController {

    private final DynamicCrowdRewardService dynamicCrowdRewardService;

    @Autowired
    public DynamicCrowdRewardController(DynamicCrowdRewardService dynamicCrowdRewardService) {
        this.dynamicCrowdRewardService = dynamicCrowdRewardService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<DynamicCrowdRewardDTO.FindAllById>> findAllRewardList(@PathVariable int crowdId) {
        // 리워드 목록 들고오기
        List<DynamicCrowdRewardDTO.FindAllById> rewards = dynamicCrowdRewardService.findAll(crowdId);
        return ResponseEntity.ok()
                .body(rewards);
    }

    @RequestMapping(value = "/{rewardId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCrowdRewardId(@PathVariable int crowdId,
                                              @PathVariable int rewardId) {
        // 특정 번호 리워드를 가져온다.(후원자가 리워드 선택시)
        DynamicCrowdRewardDTO.FindAllById findRewardId = dynamicCrowdRewardService.findByRewardId(crowdId, rewardId);
        if(findRewardId == null) {
            return new ResponseEntity<>("이 리워드는 마감되었습니다.", HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(findRewardId);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> insertCrowdReward(@RequestBody DynamicCrowdRewardDTO.Update crowdRewardInsertDTO) {
        dynamicCrowdRewardService.save(crowdRewardInsertDTO);
        return ResponseEntity.ok("리워드가 등록되었습니다.");
    }

    @RequestMapping(value = "/{rewardId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> updateCrowdReward(@PathVariable int rewardId,
                                                    @RequestBody DynamicCrowdRewardDTO.Update crowdRewardUpdateDTO) {
        crowdRewardUpdateDTO.setRewardId(rewardId);
        dynamicCrowdRewardService.update(crowdRewardUpdateDTO);

        return ResponseEntity.ok("리워드가 수정되었습니다.");
    }

    @RequestMapping(value = "/{rewardId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCrowdReward(@PathVariable int rewardId,
                                                    @PathVariable int crowdId) {
        dynamicCrowdRewardService.deleteByCrowdRewardId(rewardId, crowdId);
        return ResponseEntity.ok("리워드가 삭제되었습니다.");
    }

}
