package com.fiveis.andcrowd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public List<String> Hello(){
        System.out.println("Fuck You");
        return List.of("Fuck You");
    }
}
