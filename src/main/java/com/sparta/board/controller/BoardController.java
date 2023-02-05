package com.sparta.board.controller;

import com.sparta.board.dto.BoardRequestsDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/posts")
    public List<BoardResponseDto> getPosts() {
        return boardService.getPosts();
    }

    @PostMapping("/api/post")
    public BoardResponseDto createPost(@RequestBody BoardRequestsDto requestsDto) {
        return boardService.createPost(requestsDto);
    }

    @GetMapping("/api/post/{id}")
    public BoardResponseDto getPost(@PathVariable Long id) {
        return boardService.getPost(id);
    }

}