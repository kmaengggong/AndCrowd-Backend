package com.fiveis.andcrowd.controller;

import com.fiveis.andcrowd.service.DynamicAndQnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/andQna")
public class DynamicAndQnaController {

    private final DynamicAndQnaService dynamicAndQnaService;

    @Autowired
    public DynamicAndQnaController(DynamicAndQnaService dynamicAndQnaService) {
        this.dynamicAndQnaService = dynamicAndQnaService;
    }


}
