package com.sennyru.onboarding.controller;

import com.sennyru.onboarding.dto.CommentCreateRequestDto;
import com.sennyru.onboarding.dto.CommentResponseDto;
import com.sennyru.onboarding.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

}
