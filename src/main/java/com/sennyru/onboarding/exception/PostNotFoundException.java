package com.sennyru.onboarding.exception;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends DomainException {
    public PostNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
