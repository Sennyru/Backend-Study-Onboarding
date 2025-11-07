package com.sennyru.onboarding.member.implement;

import com.sennyru.onboarding.member.domain.Member;
import com.sennyru.onboarding.member.exception.InvalidPasswordException;
import com.sennyru.onboarding.member.exception.MemberNotFoundException;
import com.sennyru.onboarding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberReader {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    
    
    public Member getMemberByEmailAndPassword(String email, String password) {
       Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new MemberNotFoundException("가입되지 않은 이메일입니다."));
       
       if (!passwordEncoder.matches(password, member.getPassword())) {
           throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
       }
       
       return member;
    }
}
