package js6team3.tbot.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import js6team3.tbot.model.report.Report;
import js6team3.tbot.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

/**
 * a REST controller for handle HTTP requests POST, GET
 * Контроллер отчетов усыновителей JSON
 */

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
@Tag(name = "API работы с ежедневными отчетами усыновителей животных", description = "Сохранение, получение отчетов")
public class ReportController {
    ReportService reportServiceImpl;

    @PostMapping
    @Operation(summary = "Загрузка ежедневного отчета усыновителя")
    @ApiResponse(responseCode = "200", description = "Отчет успешно загружен")
    @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры некорректны")
    @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка")
    public ResponseEntity<Report> createReport(@RequestBody Report report) throws IOException {
        return ResponseEntity.ok().body(reportServiceImpl.createReport(report));
    }

    @GetMapping
    @Operation(summary = "Получение отчетов усыновителей за день")
    @ApiResponse(responseCode = "200", description = "Отчеты успешно получены")
    @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры некорректны")
    @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка")
    public ResponseEntity<Optional<Report>> dailyReport() {
        return ResponseEntity.ok().body(reportServiceImpl.dailyReport());
    }
}