package com.fiveis.andcrowd.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @RequestMapping(value="/mailAuth", method=RequestMethod.POST)
    public ResponseEntity<?> getEmailAuthNumber(@RequestBody String userEmail){
        System.out.println(userEmail);
        return null;
    }
}
