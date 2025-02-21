package com.etax.controller;

import com.etax.model.LogSCH;
import com.etax.service.LogSCHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LogSCHController {

    @Autowired
    private LogSCHService logSCHService;

    @GetMapping("/Allsch")
    public List<LogSCH> getAllLogs() {
        return logSCHService.getAllLogSCH();
    }
}
