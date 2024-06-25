package com.amalitechnss.Lizzy_fileServer.Exceptions.Exceptions;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DocumentTitleExistsException.class)

    public HttpEntity<String> DocumentExistsExceptionHandler (DocumentTitleExistsException existsException ,WebRequest request) {



        return new ResponseEntity<>(existsException.getMessage(), HttpStatus.BAD_REQUEST);

    }


@ExceptionHandler(DocumentNotFoundException.class)
    public HttpEntity<String> DocumentNotFoundExceptionHandler (DocumentNotFoundException notFoundException ,WebRequest request) {



        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NO_CONTENT);

    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public HttpEntity<String> BadCredentialsExceptionHandler () {

        return new ResponseEntity<>(" invalid login credentials", HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(DisabledException.class)
    public HttpEntity<String> AccountDisabledExceptionHandler () {

        return new ResponseEntity<>(" user account is not enabled", HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(InvalidAccountVerificationException.class)
    public HttpEntity<String> InvalidTokenExceptionHandler (InvalidAccountVerificationException invalidAccountVerificationException ) {

        return new ResponseEntity<>(invalidAccountVerificationException.getMessage(),HttpStatus.UNAUTHORIZED);

    }




}
