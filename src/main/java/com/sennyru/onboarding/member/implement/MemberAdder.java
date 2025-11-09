package com.sennyru.onboarding.member.implement;

import com.sennyru.onboarding.member.domain.Member;
import com.sennyru.onboarding.member.service.dto.AddMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAdder {
    private final PasswordEncoder passwordEncoder;
    private final MemberWriter memberWriter;
    
    
    public Member addMember(AddMemberDto requestDto) {
        String encryptedPassword = passwordEncoder.encode(requestDto.password());
        Member member = Member.create(requestDto.email(), encryptedPassword, requestDto.username());
        return memberWriter.save(member);
    }
}
