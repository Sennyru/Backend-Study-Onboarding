package com.sennyru.onboarding.member.exception;

import com.sennyru.onboarding.global.exception.DomainException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
