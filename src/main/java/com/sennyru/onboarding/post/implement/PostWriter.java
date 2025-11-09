package com.sennyru.onboarding.post.implement;

import com.sennyru.onboarding.post.domain.Post;
import com.sennyru.onboarding.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class PostWriter {
    private final PostRepository postRepository;
    
    
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
