package com.sennyru.onboarding.member.implement;

import com.sennyru.onboarding.member.domain.Member;
import com.sennyru.onboarding.member.exception.EmailAlreadyExistsException;
import com.sennyru.onboarding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class MemberWriter {
    private final MemberRepository memberRepository;
    
    
    public Member save(Member member) {
        try {
            return memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyExistsException("이미 사용 중인 이메일입니다.");
        }
    }
}
