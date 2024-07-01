package com.amalitechnss.Lizzy_fileServer.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

public class AuthenticationRequest {
@Getter
@Setter
        @NotEmpty(message = "email   is required")
        @Email(message = "email is not in the correct format")
        private  String email;
        @NotEmpty(message = "password  is required")
        private  String password;


    public @NotEmpty(message = "password  is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "password  is required") String password) {
        this.password = password;
    }
}





