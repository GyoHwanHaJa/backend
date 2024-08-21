package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.Option;
import com.exchangeBE.exchange.entity.Report.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    Optional<Option> findByStageAndOptionOrder(Stage stage, Integer optionOrder);
}
