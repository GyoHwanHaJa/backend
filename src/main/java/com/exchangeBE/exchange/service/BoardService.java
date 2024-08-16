package com.exchangeBE.exchange.service;


import com.exchangeBE.exchange.dto.BoardRequestDTO;
import com.exchangeBE.exchange.dto.BoardResponseDTO;
import com.exchangeBE.exchange.dto.CommentRequestDTO;
import com.exchangeBE.exchange.dto.CommentResponseDTO;
import com.exchangeBE.exchange.entity.Board;
import com.exchangeBE.exchange.entity.Comment;
import com.exchangeBE.exchange.entity.User;
import com.exchangeBE.exchange.repository.BoardRepository;
import com.exchangeBE.exchange.repository.CommentRepository;
import com.exchangeBE.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<BoardResponseDTO> getAllBoards() {
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        return boards.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public BoardResponseDTO createBoard(Long userId, BoardRequestDTO requestDTO) {

        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 게시글 생성
        Board board = new Board();
        board.setTitle(requestDTO.getTitle());
        board.setContent(requestDTO.getContent());
        board.setUser(user); // 사용자 설정
        board.setBoardType(requestDTO.getBoardType());
        board.setCreatedAt(LocalDateTime.now());
        board.setModifiedAt(LocalDateTime.now());
        board.setLikes(0); // 초기 좋아요 수
        board.setViews(0); // 초기 조회수
        boardRepository.save(board);
        return convertToResponseDTO(board);
    }

    public Optional<BoardResponseDTO> getBoard(Long id) {
        return boardRepository.findById(id).map(board -> {
            board.setViews(board.getViews() + 1); // 조회수 증가
            boardRepository.save(board); // 업데이트
            return convertToResponseDTO(board);
        });
    }

    public Optional<BoardResponseDTO> updateBoard(Long id, BoardRequestDTO requestDTO) {
        return boardRepository.findById(id).map(board -> {
            board.setTitle(requestDTO.getTitle());
            board.setContent(requestDTO.getContent());
            board.setBoardType(requestDTO.getBoardType());
            board.setModifiedAt(LocalDateTime.now());
            boardRepository.save(board);
            return convertToResponseDTO(board);
        });
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public CommentResponseDTO addComment(Long boardId, CommentRequestDTO commentRequestDTO) {
        // 게시글 조회
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        // 댓글 생성
        Comment newComment = new Comment();
        newComment.setContent(commentRequestDTO.getContent());
        newComment.setBoard(board);
        newComment.setCreatedAt(LocalDateTime.now());

        // 댓글 저장
        Comment savedComment = commentRepository.save(newComment);
        return convertToResponseDTO(savedComment);
    }

    public CommentResponseDTO addReply(Long boardId, CommentRequestDTO commentRequestDTO) {
        // 게시글 조회
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        // 사용자 조회 (이름으로 사용자 찾기)
        User user = userRepository.findByUsername(commentRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));


        // 부모 댓글 조회
        Comment parentComment = commentRepository.findById(commentRequestDTO.getParentId())
                .orElseThrow(() -> new RuntimeException("Parent comment not found"));

        // 대댓글 생성
        Comment replyComment = new Comment();
        replyComment.setContent(commentRequestDTO.getContent());
        replyComment.setBoard(board);
        replyComment.setParentComment(parentComment); // 부모 댓글 설정
        replyComment.setCreatedAt(LocalDateTime.now());

        // 대댓글 저장
        Comment savedReply = commentRepository.save(replyComment);
        return convertToResponseDTO(savedReply);
    }

    public void likeBoard(Long id) {
        boardRepository.findById(id).ifPresent(board -> {
            board.setLikes(board.getLikes() + 1); // 좋아요 수 증가
            boardRepository.save(board);
        });
    }

    // 게시글에서 댓글 불러오기 위한 기능
//    private List<CommentResponseDTO> getCommentsForBoard(Board board) {
//        return board.getCommentList().stream()
//                .map(comment -> new CommentResponseDTO(comment.getId(), comment.getContent(),comment.getUser().getUsername(), comment.getCreatedAt(), comment.getParentComment() != null ? comment.getParentComment().getId() : null))
//                .collect(Collectors.toList());
//    }

    private BoardResponseDTO convertToResponseDTO(Board board) {
        return BoardResponseDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardType(board.getBoardType())
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .likes(board.getLikes())
                .views(board.getViews())
                .username(board.getUser().getUsername())
//                .commentResponseDTOList(getCommentsForBoard(board))
                .build();
    }

    private CommentResponseDTO convertToResponseDTO(Comment comment) {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .parentId(comment.getParentComment().getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
