package js6team3.tbot.repository;

import js6team3.tbot.entity.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface ReportRepository extends JpaRepository<Report,Long> {
    List<Report> getAllBy();
}