package com.fiveis.andcrowd.controller.etc;

import com.fiveis.andcrowd.dto.etc.InfoBoardDTO;
import com.fiveis.andcrowd.entity.etc.InfoBoard;
import com.fiveis.andcrowd.service.etc.InfoBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/infoboard")
public class InfoBoardController {

    private final InfoBoardService infoBoardService;

    @Autowired
    public InfoBoardController(InfoBoardService infoBoardService) {
        this.infoBoardService = infoBoardService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<InfoBoardDTO.Find> getlist() {
        return infoBoardService.findAllByIsDeletedFalseOrderByInfoIdDesc();
    } // 공지사항 글 전체 조회

    @GetMapping("/detail/{infoId}")
    public ResponseEntity<?> detail(@PathVariable int infoId) {
        Optional<InfoBoardDTO.Find> findList = infoBoardService.findById(infoId);

        if(!findList.isPresent()) {
            return new ResponseEntity<>("조회되지 않는 공지글 입니다.", HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(findList.get());
        }
    }// 특정 infoId의 상세글 조회

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createInfoBoard(@RequestBody InfoBoard infoBoard) {
        infoBoardService.save(infoBoard);
        return ResponseEntity.ok("공지글이 정상적으로 등록되었습니다.");
    } // 공지글 업로드

    @RequestMapping(value = "/{infoId}/update", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> updateInfoBoard(@RequestBody InfoBoard infoBoard) {
        infoBoardService.update(infoBoard);
        return ResponseEntity.ok("게시글이 수정되었습니다.");
    } // 공지글 수정

    @PatchMapping(value = "/{infoId}")
    public String deleteInfoBoard(@PathVariable("infoId") int infoId) {
        infoBoardService.deleteById(infoId);
        ResponseEntity.ok("글이 삭제되었습니다.");
        return "redirect:/infoboard/list";
    } // 공지글 삭제
}
