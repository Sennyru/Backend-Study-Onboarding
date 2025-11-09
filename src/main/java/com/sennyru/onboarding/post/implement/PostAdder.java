package com.sennyru.onboarding.post.implement;

import com.sennyru.onboarding.member.domain.Member;
import com.sennyru.onboarding.member.implement.MemberReader;
import com.sennyru.onboarding.post.domain.Post;
import com.sennyru.onboarding.post.service.dto.AddPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostAdder {
    private final MemberReader memberReader;
    private final PostWriter postWriter;
    
    
    public Post AddPost(AddPostDto requestDto) {
        Member member = memberReader.getMemberByEmailAndPassword(requestDto.email(), requestDto.password());
        Post post = Post.create(requestDto.title(), requestDto.content(), member);
        return postWriter.save(post);
    }
}
