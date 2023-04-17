package js6team3.tbot.controllers;

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
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
public class ReportController {
    ReportService reportServiceImpl;

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) throws IOException {
        return ResponseEntity.ok().body(reportServiceImpl.createReport(report));
    }

    @GetMapping
    public ResponseEntity<Optional<Report>> dailyReport(){
        return ResponseEntity.ok().body(reportServiceImpl.dailyReport());
    }
}