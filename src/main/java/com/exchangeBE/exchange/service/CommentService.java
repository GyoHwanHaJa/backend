package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.CommentDto;
import com.exchangeBE.exchange.entity.CommentEntity;
import com.exchangeBE.exchange.entity.TravelEntity;
import com.exchangeBE.exchange.entity.UserEntity;
import com.exchangeBE.exchange.repository.CommentRepository;
import com.exchangeBE.exchange.repository.TravelRepository; // 수정된 부분
import com.exchangeBE.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TravelRepository travelRepository; // TripPostRepository -> TravelRepository로 수정

    public CommentDto saveComment(CommentDto commentDto, Long userId, Long travelPostId) { // 수정된 부분
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        TravelEntity travelPost = travelRepository.findById(travelPostId) // 수정된 부분
                .orElseThrow(() -> new RuntimeException("Travel post not found"));

        CommentEntity comment = new CommentEntity();
        comment.setUserId(userId);
        comment.setTravelPost(travelPost); // 수정된 부분
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);

        return convertToDto(comment);
    }

    public CommentDto updateComment(Long commentId, CommentDto commentDto, Long userId, Long travelPostId) { // 수정된 부분
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        TravelEntity travelPost = travelRepository.findById(travelPostId) // 수정된 부분
                .orElseThrow(() -> new RuntimeException("Travel post not found"));

        comment.setUserId(userId);
        comment.setTravelPost(travelPost); // 수정된 부분
        comment.setContent(commentDto.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);

        return convertToDto(comment);
    }

    public List<CommentDto> findAllComments() {
        return commentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CommentDto findCommentById(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return convertToDto(comment);
    }

    public void deleteComment(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }

    // Entity를 DTO로 변환하는 유틸리티 메서드
    private CommentDto convertToDto(CommentEntity comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setUserId(comment.getUserId());
        dto.setTravelPostId(comment.getTravelPost().getId()); // 수정된 부분
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());
        return dto;
    }
}
