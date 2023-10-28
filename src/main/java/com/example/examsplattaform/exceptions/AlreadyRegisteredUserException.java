package com.example.examsplattaform.exceptions;

public class AlreadyRegisteredUserException extends Exception{
    public AlreadyRegisteredUserException(String message) {
        super(message);
    }
}
