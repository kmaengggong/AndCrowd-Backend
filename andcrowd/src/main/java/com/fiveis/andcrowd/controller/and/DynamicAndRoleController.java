package com.fiveis.andcrowd.controller.and;

import com.fiveis.andcrowd.dto.and.DynamicAndRoleDTO;
import com.fiveis.andcrowd.service.and.DynamicAndRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/andRole/{andId}")
public class DynamicAndRoleController {

    private final DynamicAndRoleService dynamicAndRoleService;

    @Autowired
    public DynamicAndRoleController(DynamicAndRoleService dynamicAndRoleService) {
        this.dynamicAndRoleService = dynamicAndRoleService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<DynamicAndRoleDTO.FindById> findAll(@RequestParam("andId") int andId) {
        return dynamicAndRoleService.findAll(andId);
    }

    @RequestMapping(value = "/{andRoleId}", method = RequestMethod.GET)
    public DynamicAndRoleDTO.FindById findByAndRoleId(@PathVariable int andRoleId,
                                                      @RequestParam("andId") int andId) {
        return dynamicAndRoleService.findByAndRoleId(andId, andRoleId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void insertDynamicAndRole(@RequestBody DynamicAndRoleDTO.Update andRoleInsertDTO) {
        dynamicAndRoleService.save(andRoleInsertDTO);
    }

    @RequestMapping(value = "/{andRoleId}/update", method = RequestMethod.PUT)
    public void updateDynamicAndRole(@RequestBody DynamicAndRoleDTO.Update andRoleUpdateDTO) {
        dynamicAndRoleService.update(andRoleUpdateDTO);
    }

    @RequestMapping(value = "/{andRoleId}/delete", method = RequestMethod.DELETE)
    public void deleteDynamicAndRole(@PathVariable int andRoleId,
                                     @RequestParam("andId") int andId) {
        dynamicAndRoleService.deleteByAndRoleId(andId, andRoleId);
    }

}
