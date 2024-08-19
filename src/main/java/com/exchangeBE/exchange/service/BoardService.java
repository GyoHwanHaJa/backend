package com.exchangeBE.exchange.service;


import com.exchangeBE.exchange.dto.BoardRequestDTO;
import com.exchangeBE.exchange.dto.BoardResponseDTO;
import com.exchangeBE.exchange.dto.HotBoardResponseDTO;
import com.exchangeBE.exchange.entity.Board;
import com.exchangeBE.exchange.entity.User;
import com.exchangeBE.exchange.repository.BoardRepository;
import com.exchangeBE.exchange.repository.CommentRepository;
import com.exchangeBE.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
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
        board.setScrap(0); // 초기 스크랩수
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


    // 좋아요 기능
    @Transactional
    public BoardResponseDTO likeBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        board.setLikes(board.getLikes() + 1);
        boardRepository.save(board);



        return convertToResponseDTO(board);
    }

    // 스크랩 기능
    @Transactional
    public BoardResponseDTO scrapBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        board.setScrap(board.getScrap() + 1);
        boardRepository.save(board);

        return convertToResponseDTO(board);
    }

    // 좋아요 취소 기능
    @Transactional
    public BoardResponseDTO cancelLikeBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        if (board.getLikes() > 0) {
            board.setLikes(board.getLikes() - 1);
        }
        boardRepository.save(board);

        return convertToResponseDTO(board);
    }

    @Transactional
    public BoardResponseDTO cancelScrapBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        if (board.getScrap() > 0) {
            board.setScrap(board.getScrap() - 1);
        }
        boardRepository.save(board);

        return convertToResponseDTO(board);
    }

    // HOT 게시물
    public List<HotBoardResponseDTO> getHotBoards() {
        // 최신순 정렬
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();

        // Hot 게시물 점수 계산
        List<HotBoardResponseDTO> hotBoards = boards.stream()
                .map(board -> {
                    int hotScore = board.getViews() + (board.getScrap() * 10);
                    return new HotBoardResponseDTO(
                            board.getId(),
                            board.getTitle(),
                            board.getUser().getUsername(),
                            board.getViews(),
                            board.getScrap(),
                            hotScore
                    );
                })
                .sorted(Comparator.comparingInt(HotBoardResponseDTO::getHotScore).reversed())
                .limit(10)
                .collect(Collectors.toList());

        return hotBoards;
    }




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
                .scrap(board.getScrap())
                .build();
    }

}
