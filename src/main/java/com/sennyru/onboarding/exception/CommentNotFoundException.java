package com.sennyru.onboarding.exception;

import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends DomainException {
    public CommentNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
