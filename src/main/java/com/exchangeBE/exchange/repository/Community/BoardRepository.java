package com.exchangeBE.exchange.repository.Community;


import com.exchangeBE.exchange.entity.Community.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc(); // 최신순 정렬
    List<Board> findByTitleContainingOrderByCreatedAtDesc(String title); //제목으로 검색기능 + 최신순정렬
}
