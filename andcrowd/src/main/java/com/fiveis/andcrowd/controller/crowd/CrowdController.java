package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdDTO;
import com.fiveis.andcrowd.entity.crowd.Crowd;
import com.fiveis.andcrowd.service.crowd.CrowdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crowd")
public class CrowdController {

    private final CrowdService crowdService;

    @Autowired
    public CrowdController(CrowdService crowdService) {
        this.crowdService = crowdService;
    }

    @GetMapping(value = "/list")
    public List<CrowdDTO.FindById> getlist() {
        return crowdService.findAllByIsDeletedFalse();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createCrowd(@RequestBody Crowd crowd) {
        crowdService.save(crowd);
        return ResponseEntity.ok("펀딩글이 등록되었습니다. 심사는 5-7일 정도 소요됩니다.");
    }

    @GetMapping(value = "/detail/{crowdId}")
    public ResponseEntity<?> getCrowd(@PathVariable("crowdId") int crowdId) {
        // 특정번호의 펀딩글 조회
        Optional<CrowdDTO.FindById> findCrowd = crowdService.findByCrowdId(crowdId);

        if (!findCrowd.isPresent()) {
            return new ResponseEntity<>("이 펀딩글은 마감되거나 등록되지 않은 펀딩 글번호입니다.", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(findCrowd.get());
        }
    }

    @RequestMapping(value = "/{crowdId}/update", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> updateCrowd(@RequestBody Crowd crowd) {
        crowdService.update(crowd);
        return ResponseEntity.ok("펀딩글이 수정되었습니다.");
    }

    @PatchMapping(value = "/{crowdId}/delete")
    public String deleteCrowd(@PathVariable("crowdId") int crowdId) {
        crowdService.deleteByCrowdId(crowdId);
        ResponseEntity.ok("펀딩글이 삭제되었습니다.");
        return "redirect:/crowd/list";
    }
}