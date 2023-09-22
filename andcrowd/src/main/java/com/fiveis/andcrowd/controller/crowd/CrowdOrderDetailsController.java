package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import com.fiveis.andcrowd.service.crowd.CrowdOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/crowd_order")
public class CrowdOrderDetailsController {

    private final CrowdOrderDetailsService crowdOrderDetailsService;

    @Autowired
    public CrowdOrderDetailsController(CrowdOrderDetailsService crowdOrderDetailsService) {
        this.crowdOrderDetailsService = crowdOrderDetailsService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET) // 관리자 권한 전체 조회 // 일반 유저는 조회 불가
    public ResponseEntity<List<CrowdOrderDetailsDTO.FindById>> getlist(@PathVariable int crowdId) {
        List<CrowdOrderDetailsDTO.FindById> orderList = crowdOrderDetailsService.findAll();
        return ResponseEntity.ok().body(orderList);//crowdId);
    }

    @RequestMapping(value = "/successorder", method = RequestMethod.POST)
    public ResponseEntity<String> insertOrder(@RequestBody CrowdOrderDetails crowdOrderDetails) {
        try{
            if(!crowdOrderDetailsService.save(crowdOrderDetails)){
                return ResponseEntity.badRequest().body("결제 정보에 오류가 있습니다.");
            }
            return ResponseEntity.ok("리워드 결제 정보 저장 성공.");
        } catch(Error e){
            return ResponseEntity.badRequest().body("결제 정보에 오류가 있습니다.");
        }
    }

    @RequestMapping(value = "/{purchaseId}", method = RequestMethod.GET)
    public ResponseEntity<CrowdOrderDetailsDTO.FindById> getOrder(@PathVariable("purchaseId") int purchaseId) {
        // 개인결제내역 조회
        Optional<CrowdOrderDetailsDTO.FindById> findById = crowdOrderDetailsService.findById(purchaseId);
        if(findById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//crowdOrderDetailsService.findById(purchaseId);
        }else {
            return ResponseEntity.ok(findById.get());
        }
    }

    @RequestMapping(value = "/{purchaseId}/update", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> updateOrder(@RequestBody CrowdOrderDetailsDTO.Update updateDTO) {
        // 수정 작업 수행
        crowdOrderDetailsService.update(updateDTO);
        return ResponseEntity.ok("주문 정보가 정상적으로 수정되었습니다.");
    }

    @RequestMapping(value = "/{purchaseId}")//, method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable("purchaseId") int purchaseId) {
        // 소프트 딜리트 구현
        crowdOrderDetailsService.deleteById(purchaseId);
        ResponseEntity.ok("결제내역이 삭제되었습니다.");
        return "redirect:/crowd_order/list";
    }

    @GetMapping(value = "/{crowdId}/reward")
    public ResponseEntity<List<CrowdOrderDetailsDTO.rewardCounts>> getRewardSales(@PathVariable("crowdId") int crowdId){
        List<CrowdOrderDetailsDTO.rewardCounts> rewardCountList = crowdOrderDetailsService.rewardSales(crowdId);
        return ResponseEntity.ok(rewardCountList);
    }

    @GetMapping(value = "/{crowdId}/total")
    public ResponseEntity<Integer> getTotalSales(@PathVariable("crowdId") int crowdId){
        List<CrowdOrderDetailsDTO.rewardCounts> rewardCountList = crowdOrderDetailsService.rewardSales(crowdId);
        int totalSales = rewardCountList.stream()
                .mapToInt(CrowdOrderDetailsDTO.rewardCounts::getRewardSale)
                .sum();

        return ResponseEntity.ok(totalSales);
    }


}