package com.amalitechnss.Lizzy_fileServer.Exceptions;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DocumentTitleExistsException.class)

    public HttpEntity<String> DocumentExistsExceptionHandler (DocumentTitleExistsException existsException ,WebRequest request) {



        return new ResponseEntity<>(existsException.getMessage(), HttpStatus.BAD_REQUEST);

    }


@ExceptionHandler(DocumentNotFoundException.class)
    public HttpEntity<String> DocumentNotFoundExceptionHandler (DocumentNotFoundException notFoundException ,WebRequest request) {



        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);

    }
}
