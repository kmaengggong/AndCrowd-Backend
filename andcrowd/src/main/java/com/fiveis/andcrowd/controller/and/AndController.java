package com.fiveis.andcrowd.controller.and;

import com.fiveis.andcrowd.dto.and.AndDTO;
import com.fiveis.andcrowd.entity.and.And;
import com.fiveis.andcrowd.service.and.AndService;
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
    public List<AndDTO.Find> getList() {
        return andService.findAllByIsDeletedFalse();
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public void createItem(@RequestBody And and) {
        andService.save(and);
    }

    @RequestMapping("/{andId}")
    public AndDTO.Find getItem(@PathVariable("andId") int andId) {
        return andService.findById(andId).orElse(null);
    }

    @RequestMapping(value="/{andId}/update" , method=RequestMethod.POST)
    public void updateAnd( @RequestBody And and) {
        andService.update(and);
    }

    @RequestMapping("/{andId}/delete")
    public void deleteItem(@PathVariable("andId") int andId) {
        andService.deleteById(andId);
    }
}
