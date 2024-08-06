package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.domain.TripPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripPostRepository extends JpaRepository<TripPost, Long> {
}
