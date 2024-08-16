package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.BoardRequestDTO;
import com.exchangeBE.exchange.dto.BoardResponseDTO;
import com.exchangeBE.exchange.dto.CommentRequestDTO;
import com.exchangeBE.exchange.dto.CommentResponseDTO;
import com.exchangeBE.exchange.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 전체 목록 조회
    @GetMapping("/api/posts")
    public ResponseEntity<List<BoardResponseDTO>> getAllBoards() {
        List<BoardResponseDTO> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    // 게시글 작성
    @PostMapping("/api/post/{id}") //id는 user_id
    public ResponseEntity<BoardResponseDTO> createBoard(@PathVariable Long id, @RequestBody BoardRequestDTO requestDTO) {
        BoardResponseDTO createdBoard = boardService.createBoard(id,requestDTO);
        return ResponseEntity.ok(createdBoard);
    }


    // 선택된 게시글 조회
    @GetMapping("/api/post/{id}")
    public ResponseEntity<BoardResponseDTO> getBoard(@PathVariable Long id) {
        return boardService.getBoard(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 선택된 게시글 수정
    @PutMapping("/api/post/{id}")
    public ResponseEntity<BoardResponseDTO> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDTO requestDTO) {
        return boardService.updateBoard(id, requestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // 선택된 게시글 삭제
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    // 게시물에 댓글 생성
    @PostMapping("/api/post/{boardId}/comments")
    public ResponseEntity<CommentResponseDTO> addComment(@PathVariable Long boardId, @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO comment = boardService.addComment(boardId, commentRequestDTO);
        return ResponseEntity.ok(comment);
    }

    // 댓글에 대댓글 생성
    @PostMapping("/api/post/{id}/comments/replies") //id는 board_id
    public ResponseEntity<CommentResponseDTO> addReply(@PathVariable Long id, @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO createdReply = boardService.addReply(id, commentRequestDTO);
        return ResponseEntity.ok(createdReply);
    }

    // 게시물에 좋아요 기능
    @PostMapping("/api/post/{id}/like")
    public ResponseEntity<Void> likeBoard(@PathVariable Long id) {
        boardService.likeBoard(id);
        return ResponseEntity.ok().build();
    }


}