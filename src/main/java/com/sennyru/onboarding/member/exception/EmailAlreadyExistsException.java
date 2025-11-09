package com.sennyru.onboarding.member.exception;

import com.sennyru.onboarding.global.exception.DomainException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
