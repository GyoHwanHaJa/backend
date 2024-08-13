package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}
