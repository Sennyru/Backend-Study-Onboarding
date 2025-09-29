package com.sennyru.onboarding.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private final String email;
    private final String password;
    private final String username;

    private SignupRequestDto(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public static SignupRequestDto of(String email, String password, String username) {
        return new SignupRequestDto(email, password, username);
    }
}
