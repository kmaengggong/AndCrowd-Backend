package com.fiveis.andcrowd.controller;

import com.fiveis.andcrowd.dto.DynamicAndQnaDTO;
import com.fiveis.andcrowd.service.DynamicAndQnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/andQna{andId}")
public class DynamicAndQnaController {

    private final DynamicAndQnaService dynamicAndQnaService;

    @Autowired
    public DynamicAndQnaController(DynamicAndQnaService dynamicAndQnaService) {
        this.dynamicAndQnaService = dynamicAndQnaService;
    }

    @RequestMapping("/list")
    public List<DynamicAndQnaDTO.FindById> getList(@PathVariable("andId") int andId){
        return dynamicAndQnaService.findAll(andId);
    }

    @RequestMapping("/{andQnaId}")
    public DynamicAndQnaDTO.FindById getItem(@PathVariable("andId") int andId, @PathVariable("andQnaId") int andQnaId) {
        return dynamicAndQnaService.findByAndQnaId(andId,andQnaId);
    }

    @RequestMapping("/create")
    public void createQna(@RequestBody DynamicAndQnaDTO.Update createQna){
        dynamicAndQnaService.save(createQna);
    }

    @RequestMapping(value = "/{andQnaId}/update", method = RequestMethod.POST)
    public String update(DynamicAndQnaDTO.Update updateQna){
        dynamicAndQnaService.update(updateQna);
        return "redirect:/andQna{andId}/" + updateQna.getAndQnaId();
    }

    @RequestMapping("/{andQnaId}/delete")
    public void deleteItem(@PathVariable("andId") int andId, @PathVariable("andQnaId") int andQnaId) {
        dynamicAndQnaService.deleteByAndQnaId(andId, andQnaId);
    }


}
