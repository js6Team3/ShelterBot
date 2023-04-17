package js6team3.tbot.service.impl;

import js6team3.tbot.entity.report.Report;
import js6team3.tbot.repository.ReportRepository;
import js6team3.tbot.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service that will be calling methods from Daily Report Repository
 * (Ежедневно усыновитель направляет в приют отчет о состоянии животного)
 */
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // Create daily report (созать отчет усыновителя)
    @Override
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    // Daily reports (отчеты усыновителей за день)
    @Override
    public Optional<Report> dailyReport() {
        List<Report> reports = reportRepository.getAll();
        return reports.stream().filter(report -> report.getDayTime().toLocalDate().isEqual(LocalDate.now()))
                .findAny();
    }
}