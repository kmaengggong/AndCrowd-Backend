package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdQnaReplyDTO;
import com.fiveis.andcrowd.service.crowd.DynamicCrowdQnaReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/crowd/{crowdId}/qna/{crowdQnaId}/qnareply")
public class DynamicCrowdQnaReplyController {
    private final DynamicCrowdQnaReplyService dynamicCrowdQnaReplyService;

    @Autowired
    public DynamicCrowdQnaReplyController(DynamicCrowdQnaReplyService dynamicCrowdQnaReplyService){
        this.dynamicCrowdQnaReplyService = dynamicCrowdQnaReplyService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<DynamicCrowdQnaReplyDTO.Find>> findAllQnaReplies(@PathVariable int crowdId,
                                                                               @PathVariable int crowdQnaId){
        List<DynamicCrowdQnaReplyDTO.Find> replies = dynamicCrowdQnaReplyService.findAllByIsDeletedFalse(crowdId, crowdQnaId);
        return ResponseEntity.ok().body(replies);
    }

    @RequestMapping(value = "/{qnaReplyId}", method = RequestMethod.GET)
    public ResponseEntity<DynamicCrowdQnaReplyDTO.Find> findByReplyId(@PathVariable int crowdId,
                                                                      @PathVariable int qnaReplyId){
        DynamicCrowdQnaReplyDTO.Find reply = dynamicCrowdQnaReplyService.findById(crowdId, qnaReplyId);
        return ResponseEntity.ok().body(reply);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> insertQnaReply(@RequestBody DynamicCrowdQnaReplyDTO.Update dynamicCrwodQnaReplyDTO){
        dynamicCrowdQnaReplyService.save(dynamicCrwodQnaReplyDTO);
        return ResponseEntity.ok("댓글 등록 완료");
    }

    @RequestMapping(value = "/{qnaReplyId}", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateQnaReply(@RequestBody DynamicCrowdQnaReplyDTO.Update dynamicCrowdQnaReplyDTOUpdate){
        dynamicCrowdQnaReplyService.update(dynamicCrowdQnaReplyDTOUpdate);
        return ResponseEntity.ok("댓글 수정 완료");
    }

    @RequestMapping(value = "/{qnaReplyId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteQnaReply(@PathVariable int crowdId, @PathVariable int qnaReplyId){
        dynamicCrowdQnaReplyService.deleteByQnaReplyId(crowdId, qnaReplyId);
        return ResponseEntity.ok("댓글 삭제 완료");
    }
}
