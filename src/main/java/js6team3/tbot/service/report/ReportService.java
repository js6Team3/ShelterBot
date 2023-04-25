package js6team3.tbot.service.report;

import com.pengrad.telegrambot.TelegramBot;
import js6team3.tbot.listener.TBotListener;

import js6team3.tbot.entity.report.Report;
import js6team3.tbot.repository.report.ReportRepository;
import lombok.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import js6team3.tbot.exception.NullValueException;

/**
 * Service for managing daily adopter's report
 *
 * @author zalex14
 */
@Service
@Data
@RequiredArgsConstructor
public class ReportService {

    private final Logger logger = LoggerFactory.getLogger(TBotListener.class);
    private TelegramBot telegramBot;

    private ReportRepository reportRepository;

    /**
     * Service to add the daily report about pets
     *
     * @param report The new daily report
     */
    public Report addReport(Report report) {
        return reportRepository.save(report);
    }

    /**
     * Search the report by id
     *
     * @param id the daily report's id
     * @return The report obj
     */
    public Report searchReport(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            return report.get();
        } else {
            throw new NullValueException("Ошибка. Отчет " + id + " не найден");
        }
    }

    /**
     * Update the report by id
     *
     * @param id the daily report's id
     * @return The report obj
     */
    public Report updateReport(Long id, Report report) {
        if (reportRepository.findById(id).isPresent()) {
            report.setId(id);
            return reportRepository.save(report);
        } else {
            throw new NullValueException("Ошибка. Отчет " + id + " не найден");
        }
    }

    /**
     * Remove the report by id
     *
     * @param id the daily report's id
     * @return The report obj
     */
    public Report deleteReport(Long id) {
        Report report = searchReport(id);
        reportRepository.delete(report);
        return report;
    }

    /**
     * Outpoint all reports
     *
     * @return The list of reports
     */
    public List<Report> allDailyReports() {
        return reportRepository.findAll();
    }
}