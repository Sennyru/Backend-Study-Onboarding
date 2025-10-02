package com.sennyru.onboarding.exception;

import org.springframework.http.HttpStatus;

public class PostAccessDeniedException extends DomainException {
    public PostAccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
