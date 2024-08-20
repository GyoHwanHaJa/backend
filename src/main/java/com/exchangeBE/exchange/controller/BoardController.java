package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.BoardRequestDTO;
import com.exchangeBE.exchange.dto.BoardResponseDTO;
import com.exchangeBE.exchange.dto.BoardSearchRequestDTO;
import com.exchangeBE.exchange.dto.HotBoardResponseDTO;
import com.exchangeBE.exchange.service.Community.BoardService;
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
        BoardResponseDTO createdBoard = boardService.createBoard(id, requestDTO);
        return ResponseEntity.ok(createdBoard);
    }


    // 선택된 게시글 조회
    @GetMapping("/api/post/{id}")
    public ResponseEntity<BoardResponseDTO> getBoard(@PathVariable Long id) {
        return boardService.getBoard(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 게시물 제목으로 게시글 조회
    //      /api/post?title=xxx
    @GetMapping("/api/post")
    public List<BoardResponseDTO> searchByTitle(@ModelAttribute BoardSearchRequestDTO boardSearchRequestDTO) {
        return boardService.searchByTitle(boardSearchRequestDTO);
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


    // 게시물에 스크랩 하는 기능
    @PostMapping("/api/post/{boardId}/scrap")
    public BoardResponseDTO scrapBoard(@PathVariable Long boardId) {
        return boardService.scrapBoard(boardId);
    }

    // 게시물 스크랩 취소 기능
    @DeleteMapping("/api/post/{boardId}/scrap")
    public BoardResponseDTO cancelScrapBoard(@PathVariable Long boardId) {
        return boardService.cancelScrapBoard(boardId);
    }

    // 게시물에 좋아요 하는 기능
    @PostMapping("/api/post/{boardId}/like")
    public BoardResponseDTO likeBoard(@PathVariable Long boardId) {
        return boardService.likeBoard(boardId);
    }

    // 게시물에 좋아요 취소 기능
    @DeleteMapping("/api/post/{boardId}/like")
    public BoardResponseDTO cancelLikeBoard(@PathVariable Long boardId) {
        return boardService.cancelLikeBoard(boardId);

    }

    // 핫 게시물 조회
    @GetMapping("/api/posts/hot")
    public ResponseEntity<List<HotBoardResponseDTO>> getHotBoards() {
        List<HotBoardResponseDTO> hotBoards = boardService.getHotBoards();
        return ResponseEntity.ok(hotBoards);
    }
}