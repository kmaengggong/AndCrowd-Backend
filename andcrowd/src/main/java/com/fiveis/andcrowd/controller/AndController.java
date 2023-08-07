package com.fiveis.andcrowd.controller;

import com.fiveis.andcrowd.dto.AndDTO;
import com.fiveis.andcrowd.entity.And;
import com.fiveis.andcrowd.service.AndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/and")
public class AndController {

    private final AndService andService;

    @Autowired
    public AndController(AndService andService) {
        this.andService = andService;
    }

    @RequestMapping("/list")
    public List<AndDTO.FindAllByUserId> getListByUserId(@RequestParam("userId") int userId) {
        return andService.findAllByUserId(userId);
    }

    @RequestMapping("/create")
    public void createItem(@RequestBody And and) {
        andService.save(and);
    }

    @RequestMapping("/{andId}")
    public AndDTO.FindById getItem(@PathVariable("andId") int andId) {
        return andService.findById(andId).orElse(null);
    }

    @RequestMapping(value="/{andId}/update", method=RequestMethod.POST)
    public String update(And and){
        andService.update(and);
        return "redirect:/and" + and.getAndId();
    }

    @RequestMapping("/{andId}/delete")
    public void deleteItem(@PathVariable("andId") int andId) {
        andService.deleteById(andId);
    }
}
