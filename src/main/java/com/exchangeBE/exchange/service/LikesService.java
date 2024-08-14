package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.common.ApiResponseDto;
import com.exchangeBE.exchange.common.ResponseUtils;
import com.exchangeBE.exchange.dto.BoardResponseDTO;
import com.exchangeBE.exchange.entity.Board;
import com.exchangeBE.exchange.entity.Likes;
import com.exchangeBE.exchange.entity.User;
import com.exchangeBE.exchange.entity.enumSet.ErrorType;
import com.exchangeBE.exchange.exception.RestApiException;
import com.exchangeBE.exchange.repository.BoardRepository;
import com.exchangeBE.exchange.repository.CommentRepository;
import com.exchangeBE.exchange.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    // 게시글 좋아요 기능
    public ApiResponseDto<BoardResponseDTO> likePost(Long id, User user) {
        // 선택한 게시글이 DB에 있는지 확인
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        // 이전에 좋아요 누른 적 있는지 확인
        Optional<Likes> found = likesRepository.findByBoardAndUser(board.get(), user);
        if (found.isEmpty()) {  // 좋아요 누른적 없음
            Likes likes = Likes.of(board.get(), user);
            likesRepository.save(likes);
        } else { // 좋아요 누른 적 있음
            likesRepository.delete(found.get()); // 좋아요 눌렀던 정보를 지운다.
            likesRepository.flush();
        }

        return ResponseUtils.ok(BoardResponseDTO.from(board.get()));
    }

}