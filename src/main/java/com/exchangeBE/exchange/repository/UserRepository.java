package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 이름으로 사용자 조회
    Optional<User> findByUsername(String username);
}
