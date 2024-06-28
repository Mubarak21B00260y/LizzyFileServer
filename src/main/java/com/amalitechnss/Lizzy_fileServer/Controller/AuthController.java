package com.amalitechnss.Lizzy_fileServer.Controller;


import com.amalitechnss.Lizzy_fileServer.Authentication.*;
import com.amalitechnss.Lizzy_fileServer.Authentication.AuthenticationRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.RegisterRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.ResetPasswordRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.SetNewPasswordRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequiredArgsConstructor
public class AuthController {

    private  final AuthenticationService authenticationService;



    @PostMapping("/api/auth/Register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> Register(@RequestBody @Validated RegisterRequest registerRequest) {

        authenticationService.RegisterUser(registerRequest);
          return  ResponseEntity.accepted().build();


    }

    @GetMapping("/api/auth/verifyRegistration")
    public void  VerifyRegistration(@RequestParam("token") String token) {
         authenticationService.ConfirmRegistration(token);
    }
  @PostMapping("api/auth/login")
    public ResponseEntity<AuthenticationResponse> Login(@RequestBody @Validated AuthenticationRequest authenticationRequest) throws Exception {
        try {
             authenticationService.Login(authenticationRequest);
        }
        catch (Exception e) {
             throw   new Exception(e.getMessage());
        }
        return  ResponseEntity.ok().body(authenticationService.Login(authenticationRequest));
        }

    @PatchMapping("api/account/resetPassword")
    public ResponseEntity<?> ResetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest, Principal connectedUser) {
        authenticationService.ResetPassword(resetPasswordRequest,connectedUser);
        return  ResponseEntity.accepted().build();
    }

    @PostMapping("api/account/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email ) throws MessagingException {
        authenticationService.ForgotPassword(email);
        return  ResponseEntity.accepted().build();
    }
    @PostMapping("api/account/ConfirmAccountRecovery")
    public ResponseEntity<?>  ConfirmAccountRecovery(@RequestParam("token") String token ) throws MessagingException {
        authenticationService.ConfirmAccountRecovery(token);
        return  ResponseEntity.accepted().build();
    }
    @PatchMapping("api/account/setPassword")
    public ResponseEntity<?>  SetPassword(@RequestBody SetNewPasswordRequest request ){
        authenticationService.SetPassword(request);
        return  ResponseEntity.accepted().build();
    }






}




