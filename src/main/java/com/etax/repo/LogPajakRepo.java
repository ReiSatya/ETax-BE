package com.etax.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etax.model.LogPajak;

public interface LogPajakRepo extends JpaRepository<LogPajak, String> {
	Optional<LogPajak> findByLogCif(String logCif);
	List<LogPajak> findByLogGenerateDateBetween(LocalDate startDate, LocalDate endDate);
    List<LogPajak> findByLogGenerateDateAfter(LocalDate startDate);
    List<LogPajak> findByLogGenerateDateBefore(LocalDate endDate);
    List<LogPajak> findByLogStatusIgnoreCase(String logStatus);
    List<LogPajak> findByLogCifAndLogPeriod(String logCif, String logPeriod);

}
