package com.etax.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etax.model.Parameter;
import com.etax.repo.ParameterRepo;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepo parameterRepository;

    public String getPdfPathReport() {
        Optional<Parameter> parameterOptional = parameterRepository.findByParameterName("pdf.etax.download.path");
        if (parameterOptional.isPresent()) {
            Parameter parameter = parameterOptional.get();
            return parameter.getParameterValue();
        } else {
            throw new RuntimeException("PDF path parameter not found");
        }
    }
}
