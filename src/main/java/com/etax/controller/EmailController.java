package com.etax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.etax.dto.EmailRequestDTO;
import com.etax.service.EmailService;
import java.time.YearMonth;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEtax")
    public ResponseEntity<Map<String, String>> sendEmails(@RequestBody EmailRequestDTO emailRequest) {

        YearMonth startYearMonth = parseYearMonth(emailRequest.getStartDate());
        YearMonth endYearMonth = emailRequest.getEndDate() != null ? parseYearMonth(emailRequest.getEndDate()) : startYearMonth;

        validateDateRange(startYearMonth, endYearMonth);

        try {
            Map<String, String> statusMap = emailService.sendEmailsWithAttachments(emailRequest.getCifs(), startYearMonth, endYearMonth);
            return ResponseEntity.ok(statusMap);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to send emails: " + e.getMessage()));
        }
    }

    private YearMonth parseYearMonth(String yearMonthString) {
        try {
            return YearMonth.parse(yearMonthString);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid year-month format. Use format YYYY-MM.");
        }
    }

    private void validateDateRange(YearMonth startYearMonth, YearMonth endYearMonth) {
        if (startYearMonth.isAfter(endYearMonth)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
    }
}
