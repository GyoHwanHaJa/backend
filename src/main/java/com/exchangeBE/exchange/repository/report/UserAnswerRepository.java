package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
}
