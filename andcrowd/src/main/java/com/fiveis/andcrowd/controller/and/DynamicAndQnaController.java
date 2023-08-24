package com.fiveis.andcrowd.controller.and;

import com.fiveis.andcrowd.dto.and.DynamicAndQnaDTO;
import com.fiveis.andcrowd.service.and.DynamicAndQnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/and/{andId}/qna")
public class DynamicAndQnaController {

    private final DynamicAndQnaService dynamicAndQnaService;

    @Autowired
    public DynamicAndQnaController(DynamicAndQnaService dynamicAndQnaService) {
        this.dynamicAndQnaService = dynamicAndQnaService;
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List<DynamicAndQnaDTO.FindById> getList(@PathVariable("andId") int andId){
        return dynamicAndQnaService.findAllNotDeleted(andId);
    }

    @RequestMapping(value = "/{andQnaId}", method = RequestMethod.GET)
    public DynamicAndQnaDTO.FindById getItem(@PathVariable("andId") int andId, @PathVariable("andQnaId") int andQnaId) {
        return dynamicAndQnaService.findByAndQnaId(andId,andQnaId);
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public void createQna(@RequestBody DynamicAndQnaDTO.Update createQna){
        dynamicAndQnaService.save(createQna);
    }

    @RequestMapping(value = "/{andQnaId}/update", method = RequestMethod.PATCH)
    public String update(
            @PathVariable("andId") int andId,
            @PathVariable("andQnaId") int andQnaId,
            @RequestBody DynamicAndQnaDTO.Update updateQna) {
        dynamicAndQnaService.update(updateQna);
        return "redirect:/and/" + andId + "/qna/" + andQnaId;
    }


//    @RequestMapping(value = "/{andQnaId}/update", method = {RequestMethod.PUT, RequestMethod.PATCH})
//    public String update(DynamicAndQnaDTO.Update updateQna){
//        dynamicAndQnaService.update(updateQna);
//        return "redirect:/andQna{andId}/" + updateQna.getAndQnaId();
//    }

    @RequestMapping(value="/{andQnaId}/delete", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable("andId") int andId, @PathVariable("andQnaId") int andQnaId) {
        dynamicAndQnaService.deleteByAndQnaId(andId, andQnaId);
    }


}
