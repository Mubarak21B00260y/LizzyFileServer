package com.amalitechnss.Lizzy_fileServer.Listener;

import com.amalitechnss.Lizzy_fileServer.Entity.User;
import com.amalitechnss.Lizzy_fileServer.Event.RegistrationCompleteEvent;
import com.amalitechnss.Lizzy_fileServer.Service.EmailService;
import com.amalitechnss.Lizzy_fileServer.Service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
 private  UserService userService;
 private EmailService emailService;
 private  final String AppMail="";



    public RegistrationCompleteEventListener(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService=emailService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user= event.getUser();

        String token = UUID.randomUUID().toString();
        userService.SaveVerificationTokenForUser(token, user);
        String Recipient = user.getEmail();
        String VerificationUrl= event.getAppUrl()+"VerifyRegistration?toke="+token;
        String Message= "Click the link to complete your account registration :"+VerificationUrl;
        String Subject= " Complete Registration";
        emailService.SendEmail(Recipient,Message, Subject,AppMail);




    }
}
