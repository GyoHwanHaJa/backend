package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.domain.TripPost;
import com.exchangeBE.exchange.dto.TripPostDto;
import com.exchangeBE.exchange.repository.TripPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripPostService {

    @Autowired
    private TripPostRepository tripPostRepository;

    public List<TripPost> findAllTrips() {
        return tripPostRepository.findAll();
    }

    public TripPost saveTrip(TripPost trip) {
        return tripPostRepository.save(trip);
    }

//    public TripPost convertToEntity(TripPostDto tripPostDto) {
//        TripPost tripPost = new TripPost();
//        tripPost.setTitle(tripPostDto.getTitle());
//        tripPost.setContent(tripPostDto.getContent());
//        // 추가적인 필드들을 설정합니다.
//        // 예: user, createdAt 등
//        return tripPost;
//    }
}
