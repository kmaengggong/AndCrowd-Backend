package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import com.fiveis.andcrowd.service.crowd.CrowdOrderDetailsService;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/crowd_order")
public class CrowdOrderDetailsController {

    private final CrowdOrderDetailsService crowdOrderDetailsService;

    @Autowired
    public CrowdOrderDetailsController(CrowdOrderDetailsService orderDetailsService) {
        this.crowdOrderDetailsService = orderDetailsService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET) // 관리자 권한 전체 조회 // 일반 유저는 조회 불가
    public ResponseEntity<List<CrowdOrderDetailsDTO.FindById>> getlist(@PathVariable int crowdId) {
        List<CrowdOrderDetailsDTO.FindById> orderList = crowdOrderDetailsService.findAll();
        return ResponseEntity.ok().body(orderList);//crowdId);
    }

    @RequestMapping(value = "/successorder", method = RequestMethod.POST)
    public ResponseEntity<String> insertOrder(@RequestBody CrowdOrderDetails crowdOrderDetails) {
        crowdOrderDetailsService.save(crowdOrderDetails);
        return ResponseEntity.ok("리워드가 결제되었습니다.");
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
    public ResponseEntity<String> updateOrder(@RequestBody CrowdOrderDetails updateOrder) {
        // 수정 작업 수행
        crowdOrderDetailsService.update(updateOrder);
        return ResponseEntity.ok("주문 정보가 정상적으로 수정되었습니다.");
    }

    @RequestMapping(value = "/{purchaseId}")//, method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable("purchaseId") int purchaseId) {
//        Optional<CrowdOrderDetailsDTO.FindById> findById = crowdOrderDetailsService.findById(purchaseId);
//
//        if (findById.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            crowdOrderDetailsService.deleteById(purchaseId);
//            return ResponseEntity.ok("주문이 삭제되었습니다.");
//        }
        crowdOrderDetailsService.deleteById(purchaseId);
        ResponseEntity.ok("결제내역이 삭제되었습니다.");
        return "redirect:/crowd_order/list";
    }

}