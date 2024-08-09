package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.dto.CommentDto;
import com.exchangeBE.exchange.entity.CommentEntity;
import com.exchangeBE.exchange.entity.TopicEntity;
import com.exchangeBE.exchange.entity.TripPostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public interface TripPostRepository extends JpaRepository<TripPostEntity, Long> {

    // Google Maps API를 사용 예정인 부분은 주석 처리합니다.
    // List<TripPostEntity> findByCountryId(Long countryId);
    // List<TripPostEntity> findByPlaceId(Long placeId);
    List<TripPostEntity> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<TripPostEntity> findByTopic(TopicEntity topic);
package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.CommentDto;
import com.exchangeBE.exchange.entity.CommentEntity;
import com.exchangeBE.exchange.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class CommentService {

        @Autowired
        private CommentRepository commentRepository;

        public List<CommentDto> findAllComments() {
            return commentRepository.findAll()
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }

        public CommentDto findCommentById(Long commentId) {
            return commentRepository.findById(commentId)
                    .map(this::convertToDto)
                    .orElse(null);
        }

        public CommentDto saveComment(CommentDto commentDto) {
            CommentEntity commentEntity = convertToEntity(commentDto);
            return convertToDto(commentRepository.save(commentEntity));
        }

        public CommentDto updateComment(Long commentId, CommentDto commentDto) {
            CommentEntity existingComment = commentRepository.findById(commentId).orElse(null);
            if (existingComment != null) {
                existingComment.setContent(commentDto.getContent());
                existingComment.setTimestamp(commentDto.getTimestamp());
                return convertToDto(commentRepository.save(existingComment));
            }
            return null;
        }

        public void deleteComment(Long commentId) {
            commentRepository.deleteById(commentId);
        }

        private CommentDto convertToDto(CommentEntity entity) {
            CommentDto dto = new CommentDto();
            dto.setId(entity.getId());
            dto.setContent(entity.getContent());
            dto.setTimestamp(entity.getTimestamp());
            return dto;
        }

        private CommentEntity convertToEntity(CommentDto dto) {
            CommentEntity entity = new CommentEntity();
            entity.setContent(dto.getContent());
            entity.setTimestamp(dto.getTimestamp());
            return entity;
        }
    }

}



