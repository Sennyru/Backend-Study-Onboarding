package com.sennyru.onboarding.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends DomainException {
    public MemberNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
