package com.amalitechnss.Lizzy_fileServer.Controller;


import com.amalitechnss.Lizzy_fileServer.Entity.User;
import com.amalitechnss.Lizzy_fileServer.Event.RegistrationCompleteEvent;
import com.amalitechnss.Lizzy_fileServer.Model.UserDTO;
import com.amalitechnss.Lizzy_fileServer.Service.AppUrlService;
import com.amalitechnss.Lizzy_fileServer.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.Flow;

@RestController

public class RegisterUserController {
    private  UserDTO userDTO;
    private UserService userService;
    private ApplicationEventPublisher eventPublisher;
    AppUrlService appUrlService;
    public RegisterUserController(UserService userService, UserDTO userDTO, ApplicationEventPublisher eventPublisher, AppUrlService appUrlService) {

        this.userService=userService;
        this.userDTO=userDTO;
        this.appUrlService=appUrlService;
        this.eventPublisher=eventPublisher;
    }

    @PostMapping("/Register")

    public ResponseEntity<String> Register(@ModelAttribute UserDTO userDTO, HttpServletRequest request) {
          try {
              User user = userService.RegisterUser(userDTO);

              eventPublisher.publishEvent( new RegistrationCompleteEvent(user, appUrlService.AppUrl(request)));
              return ResponseEntity.ok().body("success");
          }
          catch (IOException e ) {
              return  ResponseEntity.internalServerError().build();

          }
    }



}
