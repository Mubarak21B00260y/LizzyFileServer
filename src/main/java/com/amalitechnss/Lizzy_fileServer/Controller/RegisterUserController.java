package com.amalitechnss.Lizzy_fileServer.Controller;


import com.amalitechnss.Lizzy_fileServer.Entity.User;
import com.amalitechnss.Lizzy_fileServer.Model.UserDTO;
import com.amalitechnss.Lizzy_fileServer.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController

public class RegisterUserController {
    private  UserDTO userDTO;
    private UserService userService;

    public RegisterUserController(UserService userService, UserDTO userDTO) {

        this.userService=userService;
        this.userDTO=userDTO;
    }

    @PostMapping("/Register")

    public ResponseEntity<String> Register(@ModelAttribute UserDTO userDTO) {
          try {
              User user = userService.RegisterUser(userDTO);
              return ResponseEntity.ok().body("success");
          }
          catch (IOException e ) {
              return  ResponseEntity.internalServerError().build();

          }
    }



}
