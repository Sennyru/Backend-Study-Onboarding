package com.sennyru.onboarding.service;

import com.sennyru.onboarding.dto.MemberResponseDto;
import com.sennyru.onboarding.domain.Member;
import com.sennyru.onboarding.dto.SignupRequestDto;
import com.sennyru.onboarding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponseDto signup(SignupRequestDto requestDto) {
        if (memberRepository.existsByEmail(requestDto.email())) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

        String encryptedPassword = passwordEncoder.encode(requestDto.password());
        Member member = Member.create(requestDto.email(), encryptedPassword, requestDto.username());
        Member savedMember = memberRepository.save(member);

        return MemberResponseDto.of(savedMember.getEmail(), savedMember.getUsername());
    }
}
