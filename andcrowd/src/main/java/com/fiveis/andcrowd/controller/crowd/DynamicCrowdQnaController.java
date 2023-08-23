package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdQnaDTO;
import com.fiveis.andcrowd.service.crowd.DynamicCrowdQnaReplyService;
import com.fiveis.andcrowd.service.crowd.DynamicCrowdQnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crowd/{crowdId}/qna")
public class DynamicCrowdQnaController {

    private DynamicCrowdQnaService dynamicCrowdQnaService;

    @Autowired
    public DynamicCrowdQnaController(DynamicCrowdQnaService dynamicCrowdQnaService){
        this.dynamicCrowdQnaService = dynamicCrowdQnaService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<DynamicCrowdQnaDTO.Find>> findAllQnas(@PathVariable int crowdId){
        List<DynamicCrowdQnaDTO.Find> qnas = dynamicCrowdQnaService.findAll(crowdId);
        return ResponseEntity.ok().body(qnas);
    }

    @RequestMapping(value = "/{crowdQnaId}", method = RequestMethod.GET)
    public ResponseEntity<DynamicCrowdQnaDTO.Find> findById(@PathVariable int crowdId, @PathVariable int crowdQnaId){
        DynamicCrowdQnaDTO.Find qna = dynamicCrowdQnaService.findById(crowdId, crowdQnaId);
        return ResponseEntity.ok().body(qna);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> insertCrowdQna(@RequestBody DynamicCrowdQnaDTO.Save dynamicCrowdQnaDTOSave){
        dynamicCrowdQnaService.save(dynamicCrowdQnaDTOSave);
        return ResponseEntity.ok("문의글 등록 완료");
    }

    @RequestMapping(value = "/{crowdQnaId}", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateCrowdQna(@RequestBody DynamicCrowdQnaDTO.Update dynamicCrowdQnaDTOUpdate){
        dynamicCrowdQnaService.update(dynamicCrowdQnaDTOUpdate);
        return ResponseEntity.ok("문의글 수정 완료");
    }

    @RequestMapping(value = "/{crowdQnaId}/delete", method = RequestMethod.PATCH)
    public ResponseEntity<String> deleteCrowdQna(@PathVariable int crowdId, @PathVariable int crowdQnaId){
        dynamicCrowdQnaService.deleteByCrowdQnaId(crowdId, crowdQnaId);
        return ResponseEntity.ok("문의글 삭제 완료");
    }
}
