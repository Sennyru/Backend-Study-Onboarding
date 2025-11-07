package com.sennyru.onboarding.post.implement;

import com.sennyru.onboarding.member.domain.Member;
import com.sennyru.onboarding.post.domain.Post;
import com.sennyru.onboarding.post.exception.PostAccessDeniedException;
import com.sennyru.onboarding.post.exception.PostNotFoundException;
import com.sennyru.onboarding.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PostReader {
    private final PostRepository postRepository;
    
    
    public Post findPostByIdAndMember(Long postId, Member member) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("존재하지 않는 게시물입니다."));
        
        if (!Objects.equals(post.getMember().getId(), member.getId())) {
            throw new PostAccessDeniedException("게시물에 대한 권한이 없습니다.");
        }
        
        return post;
    }
}
