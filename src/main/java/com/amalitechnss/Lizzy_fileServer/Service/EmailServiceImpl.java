package com.amalitechnss.Lizzy_fileServer.Service;

import com.amalitechnss.Lizzy_fileServer.Service.Enums.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService{

       private  final JavaMailSender javaMailSender;
       private  final  TemplateEngine templateEngine;


    public void SendEmail(String Recipient, String Subject ){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(Recipient);
        simpleMailMessage.setSubject(Subject);
        javaMailSender.send(simpleMailMessage);
    }
    public  void SendAttachmentMail(String Recipient, String Subject, File file, EmailTemplate template) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED,StandardCharsets.UTF_8.name());


         Context context = new Context();
         String  mailTemplate= templateEngine.process(template.getName(),context);
        helper.setTo(Recipient);
        helper.setSubject(Subject);
        helper.setText(mailTemplate,true);
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment(file.getName(),fileSystemResource);
        javaMailSender.send(mimeMessage);

    }
    public  void SendCompleteRegistrationEmail(String Recipient, String username, EmailTemplate emailTemplate, String VerificationCode, String Subject) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());

         Map <String,Object>  properties  = new HashMap<>();

         properties.put("username", username);
         properties.put("Verification_code",VerificationCode);


        Context context= new Context();
        context.setVariables(properties);


          String mailTemplate=templateEngine.process((emailTemplate.name()),context);

        helper.setTo(Recipient);
        helper.setSubject(Subject);
        helper.setText(mailTemplate,true);
        javaMailSender.send(mimeMessage);

    }


    public  void SendAccountRecoveryEmail(String Recipient, String username, EmailTemplate emailTemplate, String VerificationCode, String Subject) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());

        Map <String,Object>  properties  = new HashMap<>();

        properties.put("username", username);
        properties.put("resetToken",VerificationCode);


        Context context= new Context();
        context.setVariables(properties);


        String mailTemplate=templateEngine.process((emailTemplate.name()),context);

        helper.setTo(Recipient);
        helper.setSubject(Subject);
        helper.setText(mailTemplate,true);
        javaMailSender.send(mimeMessage);

    }





}

