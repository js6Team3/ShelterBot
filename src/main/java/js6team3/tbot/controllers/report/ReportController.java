package js6team3.tbot.controllers.report;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import js6team3.tbot.entity.report.Report;
import js6team3.tbot.entity.shelter.Shelter;
import js6team3.tbot.service.report.ReportService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * a REST controller for handle HTTP requests for daily reports
 *
 * @author zalex14
 */

@RestController
@RequestMapping("/api/report")
@Tag(name = "Операции с ежедневными отчетами усыновителей", description = "CRUD: Сохранение, удаление, " +
        "редактирование, получение отчетов")
public class ReportController {
    ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Add the daily report
     *
     * @return the report
     */
    @PostMapping
    @Operation(summary = "Загрузка ежедневного отчета усыновителя")
    @ApiResponse(responseCode = "200", description = "Отчет успешно загружен")
    @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры некорректны")
    @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка")
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        return ResponseEntity.ok().body(reportService.addReport(report));
    }

    /**
     * Get a report by id
     *
     * @return the report
     */
    @GetMapping("/get/{id}")
    @Operation(summary = "Получение отчетов усыновителей за день")
    @ApiResponse(responseCode = "200", description = "Отчеты успешно получены")
    @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры некорректны")
    @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка")
    public Report getReport(@Parameter(description = "id отчета", example = "1") @PathVariable("id") Long id) {
        return this.reportService.searchReport(id);
    }

    /**
     * Get a list of all reports
     *
     * @return a list of reports
     */
    @Operation(
            summary = "Вывести все отчеты",
            responses = {@ApiResponse(responseCode = "200", description = "ОК. Получены отчеты новых хозяев",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Shelter.class))))}, tags = "SHELTER")
    @GetMapping("/all")
    public Collection<Report> allReports() {
        return this.reportService.allDailyReports();
    }

    /**
     * The report's update
     *
     * @param id     The daily report's id
     * @param report The daily report obj
     * @return The report obj
     */
    @Operation(summary = "Редактировать ежедневный отчет",
            responses = {@ApiResponse(responseCode = "200", description = "ОК. Информация обновлена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Shelter.class))))}, tags = "SHELTER")
    @PutMapping("/{id}")
    public Report updateReport(@Parameter(description = "Отчет id", example = "1") @PathVariable("id") Long id,
                               @io.swagger.v3.oas.annotations.parameters.RequestBody Report report) {
        return this.reportService.updateReport(id, report);
    }

    /**
     * The report remove
     *
     * @param id The report id
     * @return Delete the report
     */
    @Operation(summary = "Удаление отчета усыновителя по id",
            responses = {@ApiResponse(responseCode = "200", description = "ОК.Отчет удален",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))}, tags = "SHELTER")
    @DeleteMapping("/{id}")
    public Report deleteReport(@Parameter(description = "id отчета", example = "1") @PathVariable("id") Long id) {
        return this.reportService.deleteReport(id);
    }
}