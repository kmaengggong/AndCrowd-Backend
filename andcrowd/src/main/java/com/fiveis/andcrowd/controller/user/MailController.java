package com.fiveis.andcrowd.controller.user;

import com.fiveis.andcrowd.service.user.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MailController {
    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService){
        this.mailService = mailService;
    }

    @RequestMapping(value="/mailAuth", method=RequestMethod.POST)
    public ResponseEntity<?> getEmailAuthNumber(@RequestBody Map<String, String> userEmail){
        System.out.println(userEmail.get("userEmail"));
        String res = mailService.setEmail(userEmail.get("userEmail"));
        System.out.println(res);
        if(res == null) return ResponseEntity.badRequest().body("Sth wrong dude");
        return ResponseEntity.ok(res);
    }
}
