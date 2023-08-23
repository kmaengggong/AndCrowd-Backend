package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdDTO;
import com.fiveis.andcrowd.entity.crowd.Crowd;
import com.fiveis.andcrowd.service.crowd.CrowdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crowd")
public class CrowdController {

    private final CrowdService crowdService;

    @Autowired
    public CrowdController(CrowdService crowdService) {
        this.crowdService = crowdService;
    }

    @GetMapping(value = "/list")
    public List<CrowdDTO.FindById> getlist(@PathVariable int userId) {
        return crowdService.findAllByIsDeletedFalse();
    }

    @PostMapping(value = "/create")
    public void createCrowd(@RequestBody Crowd crowd) {
        crowdService.save(crowd);
    }

    @RequestMapping(value = "/{crowdId}", method = RequestMethod.GET)
    public CrowdDTO.FindById getCrowd(@PathVariable int crowdId) {
        return crowdService.findByCrowdId(crowdId).orElse(null);
    }

    @PatchMapping(value = "/{crowdId}/update")
    public void updateCrowd(@RequestBody Crowd crowd) {
        crowdService.update(crowd);
    }

    @DeleteMapping(value = "/{crowdId}/delete")
    public void deleteCrowd(@PathVariable int crowdId) {
        crowdService.deleteByCrowdId(crowdId);
    }
}
