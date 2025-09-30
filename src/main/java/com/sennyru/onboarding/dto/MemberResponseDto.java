package com.sennyru.onboarding.dto;

public record MemberResponseDto(String email, String username) {
    public static MemberResponseDto of(String email, String username) {
        return new MemberResponseDto(email, username);
    }
}
