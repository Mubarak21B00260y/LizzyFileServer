package com.amalitechnss.Lizzy_fileServer.Listener;

import com.amalitechnss.Lizzy_fileServer.Entity.User;
import com.amalitechnss.Lizzy_fileServer.Event.RegistrationCompleteEvent;
import com.amalitechnss.Lizzy_fileServer.Service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
 private  UserService userService;

    public RegistrationCompleteEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user= event.getUser();
        String token = UUID.randomUUID().toString();
        userService.SaveVerificationTokensForUser(token, user);

    }
}
