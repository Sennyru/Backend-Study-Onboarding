package com.sennyru.onboarding.member.controller.dto;

public record MemberResponseDto(String email, String username) {
    public static MemberResponseDto of(String email, String username) {
        return new MemberResponseDto(email, username);
    }
}
