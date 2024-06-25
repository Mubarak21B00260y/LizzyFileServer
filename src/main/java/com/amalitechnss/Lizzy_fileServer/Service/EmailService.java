package com.amalitechnss.Lizzy_fileServer.Service;

import com.amalitechnss.Lizzy_fileServer.Service.Enums.EmailTemplate;
import jakarta.mail.MessagingException;

import java.io.File;

public interface EmailService {
    void  SendCompleteRegistrationEmail(String Recipient, String username, EmailTemplate emailTemplate,  String VerificationCode, String Subject) throws MessagingException;
    void SendAccountRecoveryEmail(String Recipient, String username, EmailTemplate emailTemplate, String VerificationCode, String Subject) throws MessagingException;
     void SendAttachmentMail(String Recipient, String Subject, File file, EmailTemplate template) throws MessagingException;
}
