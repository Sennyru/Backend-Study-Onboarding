package com.sennyru.onboarding.service;

import com.sennyru.onboarding.domain.Member;
import com.sennyru.onboarding.domain.Post;
import com.sennyru.onboarding.dto.PostCreateRequestDto;
import com.sennyru.onboarding.dto.PostDeleteRequestDto;
import com.sennyru.onboarding.dto.PostResponseDto;
import com.sennyru.onboarding.dto.PostUpdateRequestDto;
import com.sennyru.onboarding.exception.PostAccessDeniedException;
import com.sennyru.onboarding.exception.PostNotFoundException;
import com.sennyru.onboarding.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;

    @Transactional
    public PostResponseDto createPost(PostCreateRequestDto requestDto) {
        Member member = memberService.authenticateAndFindMember(requestDto.email(), requestDto.password());

        Post post = Post.create(requestDto.title(), requestDto.content(), member);
        Post savedPost = postRepository.save(post);

        return PostResponseDto.of(
            savedPost.getId(),
            savedPost.getMember().getEmail(),
            savedPost.getTitle(),
            savedPost.getContent()
        );
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Member member = memberService.authenticateAndFindMember(requestDto.email(), requestDto.password());
        Post post = findPostAndValidateOwnership(postId, member);

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
        Member member = memberService.authenticateAndFindMember(requestDto.email(), requestDto.password());
        Post post = findPostAndValidateOwnership(postId, member);

        postRepository.delete(post);
    }
    
    
    /**
     * 게시물이 존재하는지 확인하고, 사용자가 게시물의 작성자인지 검증합니다.
     * @param postId 검증할 게시물의 ID
     * @param member 인증된 사용자 Member 엔티티
     * @return 검증된 Post 엔티티
     * @throws PostNotFoundException 게시물이 존재하지 않을 경우
     * @throws PostAccessDeniedException 게시물에 대한 권한이 없을 경우
     */
    private Post findPostAndValidateOwnership(Long postId, Member member) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new PostNotFoundException("존재하지 않는 게시물입니다."));

        if (!Objects.equals(post.getMember().getId(), member.getId())) {
            throw new PostAccessDeniedException("게시물에 대한 권한이 없습니다.");
        }
        return post;
    }
}
