package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.Schedule.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
