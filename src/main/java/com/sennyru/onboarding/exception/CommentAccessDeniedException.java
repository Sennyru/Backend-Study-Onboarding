package com.sennyru.onboarding.exception;

import org.springframework.http.HttpStatus;

public class CommentAccessDeniedException extends DomainException {
    public CommentAccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
