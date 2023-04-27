package js6team3.tbot.repository.report;

import js6team3.tbot.entity.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository: Report
 * Implement report access layer for daily reports
 *
 * @author zalex14
 * @version 1.0
 */
public interface ReportRepository extends JpaRepository<Report, Long> {
}