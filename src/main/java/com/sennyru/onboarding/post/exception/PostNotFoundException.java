package com.sennyru.onboarding.post.exception;

import com.sennyru.onboarding.global.exception.DomainException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends DomainException {
    public PostNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
