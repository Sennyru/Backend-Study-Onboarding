package com.sennyru.onboarding.post.exception;

import com.sennyru.onboarding.global.exception.DomainException;
import org.springframework.http.HttpStatus;

public class PostAccessDeniedException extends DomainException {
    public PostAccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
