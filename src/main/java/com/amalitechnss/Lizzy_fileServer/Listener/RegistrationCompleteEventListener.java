package com.amalitechnss.Lizzy_fileServer.Listener;

import com.amalitechnss.Lizzy_fileServer.Authentication.AccountTokenService;

import com.amalitechnss.Lizzy_fileServer.Event.RegistrationCompleteEvent;

import com.amalitechnss.Lizzy_fileServer.Service.EmailService;
import com.amalitechnss.Lizzy_fileServer.Service.Enums.EmailTemplate;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Slf4j
@RequiredArgsConstructor
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
 private final EmailService emailService;

 private  final AccountTokenService accountTokenService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
     var  ConfirmToken= accountTokenService.CreateAndSaveVerificationToken(event.getUser());
        String Recipient = event.getEmail();
        String username= event.getUser().getFirstname();

        String Subject= " Complete Registration";
        try {
            emailService.SendCompleteRegistrationEmail(Recipient,username, EmailTemplate.Confirm_Account,ConfirmToken,Subject);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


}
