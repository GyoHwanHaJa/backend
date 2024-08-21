package com.exchangeBE.exchange.service.Community;

import com.exchangeBE.exchange.dto.CommentRequestDTO;
import com.exchangeBE.exchange.dto.CommentResponseDTO;
import com.exchangeBE.exchange.entity.Community.Board;
import com.exchangeBE.exchange.entity.Community.Comment;
import com.exchangeBE.exchange.entity.User.User;
import com.exchangeBE.exchange.repository.Community.BoardRepository;
import com.exchangeBE.exchange.repository.Community.CommunityCommentRepository;
import com.exchangeBE.exchange.repository.Community.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunityCommentService {
    @Autowired
    private CommunityCommentRepository communityCommentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CommentResponseDTO addComment(Long boardId, CommentRequestDTO request) {
        // 사용자 조회 (nickname으로)
        User user = userRepository.findBynickname(request.getNickname())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 게시판 조회
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        // 댓글 생성
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setUser(user);
        comment.setBoard(board);
        comment.setParentComment(null); // 일반 댓글은 부모 댓글이 없음

        // 댓글 저장
        communityCommentRepository.save(comment);

        // 댓글 응답 DTO 생성
        return convertToResponseDTO(comment);
    }

    @Transactional
    public CommentResponseDTO addReply(Long boardId, CommentRequestDTO request) {
        // 사용자 조회 (nickname으로)
        User user = userRepository.findBynickname(request.getNickname())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 게시판 조회
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        // 부모 댓글 조회
        Comment parentComment = communityCommentRepository.findById(request.getParentId())
                .orElseThrow(() -> new RuntimeException("Parent comment not found"));

        // 대댓글 생성
        Comment reply = new Comment();
        reply.setContent(request.getContent());
        reply.setUser(user);
        reply.setBoard(board);
        reply.setParentComment(parentComment);

        // 대댓글 저장
        communityCommentRepository.save(reply);

        // 대댓글 응답 DTO 생성
        return convertToResponseDTO(reply);
    }

    private CommentResponseDTO convertToResponseDTO(Comment comment) {
        CommentResponseDTO responseDTO = new CommentResponseDTO();
        responseDTO.setId(comment.getId());
        responseDTO.setContent(comment.getContent());
        responseDTO.setId(comment.getUser().getId());
        responseDTO.setNickname(comment.getUser().getNickname());
        responseDTO.setCreatedAt(comment.getCreatedAt());
        if (comment.getParentComment() != null) {
            responseDTO.setParentId(comment.getParentComment().getId()); // 부모 댓글 ID 설정
        } else {
            responseDTO.setParentId(null); // 일반 댓글의 경우 null
        }

        responseDTO.setReplies(new ArrayList<>()); // 대댓글 리스트 초기화
        return responseDTO;
    }


    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getComments(Long boardId) {
        List<Comment> comments = communityCommentRepository.findByBoardId(boardId);
        Map<Long, CommentResponseDTO> responseMap = new HashMap<>();
        List<CommentResponseDTO> responseList = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponseDTO responseDTO = convertToResponseDTO(comment);
            responseMap.put(comment.getId(), responseDTO);

            if (comment.getParentComment() == null) {
                responseList.add(responseDTO);
            }
        }

        for (Comment comment : comments) {
            if (comment.getParentComment() != null) {
                CommentResponseDTO parentResponse = responseMap.get(comment.getParentComment().getId());
                if (parentResponse != null) {
                    List<CommentResponseDTO> replies = parentResponse.getReplies();
                    if (replies == null) {
                        replies = new ArrayList<>();
                    }
                    replies.add(convertToResponseDTO(comment));
                    parentResponse.setReplies(replies);
                }
            }
        }

        return responseList;
    }
}