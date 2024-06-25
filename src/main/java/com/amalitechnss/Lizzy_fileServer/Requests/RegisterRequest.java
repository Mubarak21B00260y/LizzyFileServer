package com.amalitechnss.Lizzy_fileServer.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;




@Setter
@Getter

public class RegisterRequest {

@NotEmpty(message = "firstname is required")
@NotBlank(message = " firstname cannot have blank spaces ")
private String firstname;
@NotEmpty(message = "lastname name is required")
@NotBlank(message = " lastname cannot have blank spaces ")
private  String lastname;
@NotEmpty(message = "password  is required")
private  String password;
@NotEmpty(message = "email   is required")
@Email(message = "email is not in the correct format")
private  String email;
//
}
