package com.amalitechnss.Lizzy_fileServer.Exceptions.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
