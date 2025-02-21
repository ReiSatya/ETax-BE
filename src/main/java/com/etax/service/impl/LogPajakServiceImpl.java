package com.etax.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etax.dto.LogPajakNasabahDTO;
import com.etax.model.LogPajak;
import com.etax.model.Nasabah;
import com.etax.repo.LogPajakRepo;
import com.etax.repo.NasabahRepo;
import com.etax.service.LogPajakService;

@Service
public class LogPajakServiceImpl implements LogPajakService {

    @Autowired
    private LogPajakRepo logPajakRepository;

    @Autowired
    private NasabahRepo nasabahRepository;

    @Override
    public List<LogPajakNasabahDTO> getLogPajakByCif(String cif) {
        List<LogPajak> LogPajak = logPajakRepository.findByLogCif(cif);

        return LogPajak.stream()
                .map(this::convertToLogPajakNasabahDTO)
                .collect(Collectors.toList());
    }

    private LogPajakNasabahDTO convertToLogPajakNasabahDTO(LogPajak logPajak) {
        LogPajakNasabahDTO dto = new LogPajakNasabahDTO();
        BeanUtils.copyProperties(logPajak, dto);

        Optional<Nasabah> nasabah = nasabahRepository.findByCif(logPajak.getLogCif());
        dto.setNasabahEmail(nasabah.get().getEmail());
        dto.setNasabahName(nasabah.get().getNama());

        return dto;
    }
}
