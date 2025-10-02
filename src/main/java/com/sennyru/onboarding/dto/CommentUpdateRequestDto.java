package com.sennyru.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRequestDto(
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password,

    @NotBlank
    String content
) {
    public static CommentUpdateRequestDto of(String email, String password, String content) {
        return new CommentUpdateRequestDto(email, password, content);
    }
}
