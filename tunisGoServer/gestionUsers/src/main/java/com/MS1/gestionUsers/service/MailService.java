package com.MS1.gestionUsers.service;

import com.MS1.gestionUsers.message.ContactForm;

public interface MailService {

    public boolean sendMail(ContactForm contactForm);
}
