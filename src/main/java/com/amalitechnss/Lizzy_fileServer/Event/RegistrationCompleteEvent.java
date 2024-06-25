package com.amalitechnss.Lizzy_fileServer.Event;

import com.amalitechnss.Lizzy_fileServer.Entity.User;
import lombok.Getter;

import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent  extends ApplicationEvent {


private final  User user;

private  String email;
    public RegistrationCompleteEvent(User user,  String email) {
        super(user);
        this.user=user;
        this.email=email;
    }
}
