package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.domain.TripPost;
import com.exchangeBE.exchange.repository.TripPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private TripPostRepository tripPostRepository;

    public List<TripPost> getAllPosts() {
        return tripPostRepository.findAll();
    }

    public TripPost getPostById(Long id) {
        return tripPostRepository.findById(id).orElse(null);
    }
}
