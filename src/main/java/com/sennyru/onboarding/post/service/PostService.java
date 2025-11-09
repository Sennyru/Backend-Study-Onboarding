package com.sennyru.onboarding.post.service;

import com.sennyru.onboarding.member.domain.Member;
import com.sennyru.onboarding.member.implement.MemberReader;
import com.sennyru.onboarding.post.controller.dto.PostCreateRequestDto;
import com.sennyru.onboarding.post.controller.dto.PostDeleteRequestDto;
import com.sennyru.onboarding.post.controller.dto.PostResponseDto;
import com.sennyru.onboarding.post.controller.dto.PostUpdateRequestDto;
import com.sennyru.onboarding.post.domain.Post;
import com.sennyru.onboarding.post.implement.PostAdder;
import com.sennyru.onboarding.post.implement.PostRemover;
import com.sennyru.onboarding.post.implement.PostReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final MemberReader memberReader;
    private final PostAdder postAdder;
    private final PostRemover postRemover;
    private final PostReader postReader;
    

    @Transactional
    public PostResponseDto createPost(PostCreateRequestDto requestDto) {
        Post savedPost = postAdder.AddPost(requestDto);
        
        return PostResponseDto.of(
            savedPost.getId(),
            savedPost.getMember().getEmail(),
            savedPost.getTitle(),
            savedPost.getContent()
        );
    }


    @Transactional
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Member member = memberReader.getMemberByEmailAndPassword(requestDto.email(), requestDto.password());
        Post post = postReader.findPostByIdAndMember(postId, member);
        post.update(requestDto.title(), requestDto.content());
        
        return PostResponseDto.of(
            post.getId(),
            post.getMember().getEmail(),
            post.getTitle(),
            post.getContent()
        );
    }
    
    @Transactional
    public void deletePost(Long postId, PostDeleteRequestDto requestDto) {
        Member member = memberReader.getMemberByEmailAndPassword(requestDto.email(), requestDto.password());
        Post post = postReader.findPostByIdAndMember(postId, member);
        postRemover.delete(post);
    }
}
