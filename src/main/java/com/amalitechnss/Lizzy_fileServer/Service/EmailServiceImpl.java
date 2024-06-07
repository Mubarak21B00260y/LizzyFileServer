package com.amalitechnss.Lizzy_fileServer.Service;

import com.amalitechnss.Lizzy_fileServer.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
@Service
public class EmailServiceImpl implements EmailService{

    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void SendEmail(String Recipient, String body, String Subject, String AppMail ){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(Recipient);
        simpleMailMessage.setFrom(AppMail);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(Subject);
        javaMailSender.send(simpleMailMessage);
    }





}

