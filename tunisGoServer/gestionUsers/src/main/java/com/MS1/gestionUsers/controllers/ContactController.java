package com.MS1.gestionUsers.controllers;

import com.MS1.gestionUsers.message.ContactForm;
import com.MS1.gestionUsers.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin
public class ContactController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendMessage")
    public boolean sendMessage(@RequestBody ContactForm contactForm) {

        return mailService.sendMail(contactForm);
    }
}
