package com.etax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etax.model.LogSCH;
import com.etax.repo.LogSCHRepo;

import java.util.List;

@Service
public class LogSCHService {

    @Autowired
    private LogSCHRepo logSCHRepository;

    public List<LogSCH> getAllLogSCH() {
        return logSCHRepository.findAll();
    }
}

