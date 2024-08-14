package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.common.ApiResponseDto;
import com.exchangeBE.exchange.common.ResponseUtils;
import com.exchangeBE.exchange.common.SuccessResponse;
import com.exchangeBE.exchange.dto.CommentRequestDTO;
import com.exchangeBE.exchange.dto.CommentResponseDTO;
import com.exchangeBE.exchange.entity.Board;
import com.exchangeBE.exchange.entity.Comment;
import com.exchangeBE.exchange.entity.User;
import com.exchangeBE.exchange.entity.enumSet.ErrorType;
import com.exchangeBE.exchange.entity.enumSet.UserRoleEnum;
import com.exchangeBE.exchange.exception.RestApiException;
import com.exchangeBE.exchange.repository.BoardRepository;
import com.exchangeBE.exchange.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성
    @Transactional
    public ApiResponseDto<CommentResponseDTO> createComment(Long id, CommentRequestDTO requestDto, User user) {

        // 선택한 게시글 DB 조회
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        Long parentCommentId = requestDto.getParentCommentId();
        Comment comment = Comment.of(requestDto, board.get(), user);
        if (parentCommentId == null) {  // parentComment 가 없다면
            commentRepository.save(comment);    // 바로 저장
            return ResponseUtils.ok(CommentResponseDTO.from(comment));
        }
        // parentComment 가 있다면 parent comment 에 childComment 를 추가
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_WRITING));
        parentComment.addChildComment(comment); // parentComment 에 childComment 추가
        commentRepository.save(comment);

        return ResponseUtils.ok(CommentResponseDTO.from(comment));

    }

    // 댓글 수정
    @Transactional
    public ApiResponseDto<CommentResponseDTO> updateComment(Long id, CommentRequestDTO requestDto, User user) {

        // 선택한 댓글이 DB에 있는지 확인
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        // 댓글의 작성자와 수정하려는 사용자의 정보가 일치하는지 확인 (수정하려는 사용자가 관리자라면 댓글 수정 가능)
        Optional<Comment> found = commentRepository.findByIdAndUser(id, user);
        if (found.isEmpty() && user.getRole() == UserRoleEnum.USER) {
            throw new RestApiException(ErrorType.NOT_WRITER);
        }

        // 관리자이거나, 댓글의 작성자와 수정하려는 사용자의 정보가 일치한다면, 댓글 수정
        comment.get().update(requestDto, user);
        commentRepository.flush();   // responseDto 에 modifiedAt 업데이트 해주기 위해 saveAndFlush 사용

        // ResponseEntity 에 dto 담아서 리턴
        return ResponseUtils.ok(CommentResponseDTO.from(comment.get()));

    }

    // 댓글 삭제
    @Transactional
    public ApiResponseDto<SuccessResponse> deleteComment(Long id, User user) {

        // 선택한 댓글이 DB에 있는지 확인
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        // 댓글의 작성자와 삭제하려는 사용자의 정보가 일치하는지 확인 (삭제하려는 사용자가 관리자라면 댓글 삭제 가능)
        Optional<Comment> found = commentRepository.findByIdAndUser(id, user);
        if (found.isEmpty() && user.getRole() == UserRoleEnum.USER) {
            throw new RestApiException(ErrorType.NOT_WRITER);
        }

        // 관리자이거나, 댓글의 작성자와 삭제하려는 사용자의 정보가 일치한다면, 댓글 삭제
        commentRepository.deleteById(id);

        // ResponseEntity 에 상태코드, 메시지 들어있는 DTO 를 담아서 반환
        return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "댓글 삭제 성공"));

    }

}