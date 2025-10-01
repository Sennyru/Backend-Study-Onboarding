package com.sennyru.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequestDto(
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
    public static PostUpdateRequestDto of(String email, String password, String title, String content) {
        return new PostUpdateRequestDto(email, password, title, content);
    }
}
