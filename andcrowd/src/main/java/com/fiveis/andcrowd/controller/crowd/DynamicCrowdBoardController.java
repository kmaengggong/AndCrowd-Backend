package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.DynamicCrowdBoardDTO;
import com.fiveis.andcrowd.service.crowd.DynamicCrowdBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crowd/{crowdId}/board")
public class DynamicCrowdBoardController {

    private DynamicCrowdBoardService dynamicCrowdBoardService;

    @Autowired
    public DynamicCrowdBoardController(DynamicCrowdBoardService dynamicCrowdBoardService){
        this.dynamicCrowdBoardService = dynamicCrowdBoardService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<DynamicCrowdBoardDTO.Find>> findAllBoards(@PathVariable int crowdId){
        List<DynamicCrowdBoardDTO.Find> boards = dynamicCrowdBoardService.findAll(crowdId);

        return ResponseEntity.ok().body(boards);
    }

    @RequestMapping(value = "/{crowdBoardId}", method = RequestMethod.GET)
    public ResponseEntity<DynamicCrowdBoardDTO.Find> findById(@PathVariable int crowdId, @PathVariable int crowdBoardId){
        DynamicCrowdBoardDTO.Find board = dynamicCrowdBoardService.findById(crowdId, crowdBoardId);

        return ResponseEntity.ok().body(board);
    }

//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public ResponseEntity<String> insertCrowdBoard(@RequestBody DynamicCrowdBoardDTO.Save dynamicCrowdBoardDTOSave){
//        dynamicCrowdBoardService.save(dynamicCrowdBoardDTOSave);
//        return  ResponseEntity.ok("공지사항 등록 완료");
//    }
}
