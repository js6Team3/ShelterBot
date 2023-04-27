package js6team3.tbot.controllers.report;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import js6team3.tbot.entity.report.Report;
import js6team3.tbot.service.report.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * a REST controller for handle HTTP requests for daily reports
 *
 * @author zalex14
 * @version 1.0
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/report")
@Tag(name = "Операции с ежедневными отчетами усыновителей", description = "CRUD: Сохранение, удаление, " +
        "редактирование, получение отчетов")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ОК. Данные успешно обработаны",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = Report.class)))),
        @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры запроса некорректны"),
        @ApiResponse(responseCode = "404", description = "Ошибка 404. Неправильный id. Результат запроса равен NULL"),
        @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка программы")
})
public class ReportController {
    ReportService reportService;

    /**
     * Add the daily report
     *
     * @return the report
     */
    @PostMapping
    @Operation(summary = "Загрузка ежедневного отчета усыновителя")
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
    public Report getReport(@Parameter(description = "id отчета", example = "1") @PathVariable("id") Long id) {
        return this.reportService.searchReport(id);
    }

    /**
     * Get a list of all reports
     *
     * @return a list of reports
     */
    @Operation(summary = "Вывести все отчеты", description = "ОК. Получены отчеты новых хозяев")
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
    @Operation(summary = "Редактировать ежедневный отчет", description = "ОК. Информация обновлена")
    @PutMapping("/{id}")
    public Report updateReport(@Parameter(description = "Отчет id", example = "1") @PathVariable("id") Long id,
                               @io.swagger.v3.oas.annotations.parameters.RequestBody Report report) {
        return this.reportService.updateReport(id, report);
    }

    /**
     * The report remove
     *
     * @param id The report id
     */
    @Operation(summary = "Удаление отчета усыновителя по id", description = "ОК.Отчет удален")
    @DeleteMapping("/{id}")
    public void deleteReport(@Parameter(description = "id отчета") @PathVariable("id") Long id) {
        reportService.deleteReport(id);
    }
}