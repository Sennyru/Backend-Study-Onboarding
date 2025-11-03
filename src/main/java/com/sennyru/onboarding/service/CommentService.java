package com.sennyru.onboarding.service;

import com.sennyru.onboarding.domain.Comment;
import com.sennyru.onboarding.domain.Member;
import com.sennyru.onboarding.domain.Post;
import com.sennyru.onboarding.dto.CommentCreateRequestDto;
import com.sennyru.onboarding.dto.CommentResponseDto;
import com.sennyru.onboarding.exception.PostNotFoundException;
import com.sennyru.onboarding.repository.CommentRepository;
import com.sennyru.onboarding.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberService memberService;

    @Transactional
    public CommentResponseDto createComment(Long postId, CommentCreateRequestDto requestDto) {
        Member member = memberService.authenticateAndFindMember(requestDto.email(), requestDto.password());
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new PostNotFoundException("댓글을 작성할 게시물이 존재하지 않습니다."));

        Comment comment = Comment.create(requestDto.content(), member, post);
        Comment savedComment = commentRepository.save(comment);

        return CommentResponseDto.of(
            savedComment.getId(),
            savedComment.getMember().getEmail(),
            savedComment.getContent()
        );
    }
}
