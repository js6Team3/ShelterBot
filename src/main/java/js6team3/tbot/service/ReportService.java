package js6team3.tbot.service;

import js6team3.tbot.entity.report.Report;

import java.io.IOException;
import java.util.Optional;

/**
 * Service for managing daily adopter's report.
 * (Отправка ежедневного отчета усыновителя в течение месяца до принятия решения об усыновлении
 * Каждый день волонтеры отсматривают все присланные отчеты после 21:00.)
 */

public interface ReportService {
    Report createReport(Report report) throws IOException;

    Optional<Report> dailyReport();
}