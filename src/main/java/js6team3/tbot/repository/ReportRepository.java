package js6team3.tbot.repository;

import js6team3.tbot.entity.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Implement report access layer
 *
 * @author Andrei Popov
 */
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> getAll();
}