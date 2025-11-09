package com.sennyru.onboarding.post.service.dto;

public record AddPostDto (
    String email,
    String password,
    String title,
    String content
) {
    public static AddPostDto of(String email, String password, String title, String content) {
        return new AddPostDto(email, password, title, content);
    }
}
