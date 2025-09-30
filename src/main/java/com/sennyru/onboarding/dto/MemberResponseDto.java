package com.sennyru.onboarding.dto;

import com.sennyru.onboarding.domain.Member;

public record MemberResponseDto(String email, String username) {
    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(member.getEmail(), member.getUsername());
    }
}