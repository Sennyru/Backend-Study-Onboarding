package com.sennyru.onboarding.member.service;

import com.sennyru.onboarding.member.controller.dto.SignupRequestDto;
import com.sennyru.onboarding.member.domain.Member;
import com.sennyru.onboarding.member.controller.dto.MemberResponseDto;
import com.sennyru.onboarding.member.implement.MemberAdder;
import com.sennyru.onboarding.member.service.dto.AddMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberAdder memberAdder;
    
    
    @Transactional
    public MemberResponseDto signup(SignupRequestDto requestDto) {
        Member savedMember = memberAdder.addMember(AddMemberDto.of(
                requestDto.email(), requestDto.password(), requestDto.username()));
        
        return MemberResponseDto.of(savedMember.getEmail(), savedMember.getUsername());
    }
}
