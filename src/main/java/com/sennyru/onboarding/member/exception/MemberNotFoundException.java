package com.sennyru.onboarding.member.exception;

import com.sennyru.onboarding.global.exception.DomainException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends DomainException {
    public MemberNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
