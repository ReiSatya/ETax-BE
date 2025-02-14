package com.etax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etax.service.CrontabService;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SchedulerController {
	
	private static final String username = "etax";

    @Autowired
    private CrontabService crontabService;

    @GetMapping("/getScheduler")
    public String getCrontab() throws IOException {
        return crontabService.getCrontabForUser(username);
    }

    @PostMapping("/updateScheduler")
    public ResponseEntity<?> updateFirstCrontab(
            @RequestParam String menit,
            @RequestParam String jam,
            @RequestParam String tanggal) throws IOException {
        try {
            String cleanedMenit = menit.replaceAll("\\s+", "");
            String cleanedJam = jam.replaceAll("\\s+", "");
            String cleanedTanggal = tanggal.replaceAll("\\s+", "");

            String crontabScheduler = cleanedMenit + " " + cleanedJam + " " + cleanedTanggal + " * * cd /opt/EtaxStatement/generate/ && ./startGenerate.sh >> /opt/EtaxStatement/generate/log/crontab.log 2>&1";
            System.out.println("Input command: " + crontabScheduler);

            crontabService.updateFirstLineForUser(username, crontabScheduler);

            String responseMessage = "Generate crontab updated successfully";
            return ResponseEntity.ok().body(responseMessage);
        } catch (Exception e) {
            String errorMessage = "Failed to update the Generate crontab. Please try again later.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("/updateSchedulerSend")
    public ResponseEntity<?> updateSecondCrontab(
            @RequestParam String menit,
            @RequestParam String jam,
            @RequestParam String tanggal) throws IOException {
        try {
            String cleanedMenit = menit.replaceAll("\\s+", "");
            String cleanedJam = jam.replaceAll("\\s+", "");
            String cleanedTanggal = tanggal.replaceAll("\\s+", "");

            String crontabScheduler = cleanedMenit + " " + cleanedJam + " " + cleanedTanggal + " * * cd /opt/EtaxStatement/send/ && ./startSend.sh >> /opt/EtaxStatement/send/log/crontab.log 2>&1";
            System.out.println("Input command: " + crontabScheduler);

            crontabService.updateSecondLineForUser(username, crontabScheduler);

            String responseMessage = "Send crontab updated successfully";
            return ResponseEntity.ok().body(responseMessage);
        } catch (Exception e) {
            String errorMessage = "Failed to update the Send crontab. Please try again later.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}


