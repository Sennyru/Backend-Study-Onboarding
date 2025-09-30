package com.sennyru.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PostCreateRequestDto(
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password,

    @NotBlank
    String title,

    @NotBlank
    String content
) {
    public static PostCreateRequestDto of(String email, String password, String title, String content) {
        return new PostCreateRequestDto(email, password, title, content);
    }
}
