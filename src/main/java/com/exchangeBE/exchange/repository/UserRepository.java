package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}