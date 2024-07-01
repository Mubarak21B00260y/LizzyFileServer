package com.amalitechnss.Lizzy_fileServer.Exceptions.Exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DocumentTitleExistsException.class)

    public HttpEntity<ErrorResponse> DocumentExistsExceptionHandler (DocumentTitleExistsException existsException) {


        return  new ResponseEntity<>(new  ErrorResponse(existsException.getMessage()),HttpStatus.BAD_REQUEST);
    }

@ExceptionHandler(DocumentNotFoundException.class)
    public HttpEntity<ErrorResponse> DocumentNotFoundExceptionHandler (DocumentNotFoundException notFoundException ) {

        return new ResponseEntity<>( new ErrorResponse(notFoundException.getMessage()),HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public HttpEntity<ErrorResponse> BadCredentialsExceptionHandler (BadCredentialsException badCredentialsException) {

        return new ResponseEntity<>(new  ErrorResponse(badCredentialsException.getMessage()),HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(DisabledException.class)
    public HttpEntity<ErrorResponse> AccountDisabledExceptionHandler (DisabledException exception) {

        return new ResponseEntity<>( new  ErrorResponse(exception.getMessage()) ,HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(InvalidAccountVerificationException.class)
    public HttpEntity<ErrorResponse> InvalidTokenExceptionHandler (InvalidAccountVerificationException invalidAccountVerificationException ) {

        return new ResponseEntity<>(new ErrorResponse(invalidAccountVerificationException.getMessage()),HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public HttpEntity<ErrorResponse> UserExistsExceptionHandler(UserAlreadyExistsException userAlreadyExistsException ) {

        return new ResponseEntity<>(new  ErrorResponse(userAlreadyExistsException.getMessage()),HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ExpiredJwtException.class)
    public HttpEntity<ErrorResponse> JwTokenExpiredException(ExpiredJwtException  expiredJwtException ) {

        return new ResponseEntity<>(new  ErrorResponse("session expired, please re-login"),HttpStatus.UNAUTHORIZED);
    }


    @Getter
    @Setter
    class ErrorResponse {

        private final String message;


        ErrorResponse(String message) {
            this.message = message;
        }
    }



}
