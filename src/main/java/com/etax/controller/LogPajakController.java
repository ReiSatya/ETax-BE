package com.etax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etax.dto.LogPajakNasabahDTO;
import com.etax.service.LogPajakService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LogPajakController {

    @Autowired
    private LogPajakService logPajakService;

    @GetMapping("/log-pajak")
    public List<LogPajakNasabahDTO> getLogPajakByCif(@RequestParam(required = false) String cif) {
        return logPajakService.getLogPajakByCif(cif);
    }
}
