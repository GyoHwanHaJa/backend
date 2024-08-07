package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.Schedule.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
