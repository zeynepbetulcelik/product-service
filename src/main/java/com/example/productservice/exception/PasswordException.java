package com.example.productservice.exception;


public class PasswordException extends RuntimeException {
    public PasswordException(final String message) {
        super(message);
    }
}