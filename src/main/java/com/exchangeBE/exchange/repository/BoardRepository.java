package com.exchangeBE.exchange.repository;


import com.exchangeBE.exchange.entity.Board;
import com.exchangeBE.exchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    Optional<Board>  findByIdAndUser(Long id, User user);
    void deleteAllByUser(User user);

}
