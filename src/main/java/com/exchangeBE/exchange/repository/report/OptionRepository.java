package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Options, Long> {
}
