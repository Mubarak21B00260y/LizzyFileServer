package com.amalitechnss.Lizzy_fileServer.Model;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {
    private String firstname;
    private  String lastname;
    private String password;
    private String matchingPassword;
    private  String email;
    private  String role;

}
