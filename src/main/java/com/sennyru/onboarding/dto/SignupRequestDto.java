package com.sennyru.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;

    @NotBlank
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
