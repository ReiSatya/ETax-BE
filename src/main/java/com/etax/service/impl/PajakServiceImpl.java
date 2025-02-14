package com.etax.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etax.dto.PajakNasabahDTO;
import com.etax.dto.StatusDeliveryDTO;
import com.etax.dto.TaxSummaryDTO;
import com.etax.dto.TaxSummaryDetailDTO;
import com.etax.model.LogPajak;
import com.etax.model.Nasabah;
import com.etax.model.Pajak;
import com.etax.repo.LogPajakRepo;
import com.etax.repo.NasabahRepo;
import com.etax.repo.PajakRepo;
import com.etax.service.PajakService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PajakServiceImpl implements PajakService {

    @Autowired
    private PajakRepo pajakRepository;
    
    @Autowired
    private NasabahRepo nasabahRepository;
    
    @Autowired
    private LogPajakRepo logPajakRepository;

    @Override
    public List<Pajak> getPajak(Long tableId, String cif, String status, LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            List<Pajak> pajakList = pajakRepository.findByParams(tableId, cif, status);
            return pajakList.stream()
                    .filter(p -> !p.getTanggalpembuatan().isBefore(startDate) && !p.getTanggalpembuatan().isAfter(endDate))
                    .toList();
        } else if (startDate != null) {
            return pajakRepository.findByParams(tableId, cif, status)
                    .stream()
                    .filter(p -> !p.getTanggalpembuatan().isBefore(startDate))
                    .toList();
        } else if (endDate != null) {
            return pajakRepository.findByParams(tableId, cif, status)
                    .stream()
                    .filter(p -> !p.getTanggalpembuatan().isAfter(endDate))
                    .toList();
        } else {

            return pajakRepository.findByParams(tableId, cif, status);
        }
    }
    
    @Override
    public PajakNasabahDTO getPajakNasabah (String cif, LocalDate startDate, LocalDate endDate) {
        Nasabah nasabah = nasabahRepository.findById(cif).orElseThrow(() -> new RuntimeException("Nasabah not found"));

        List<Pajak> pajakList;
        if (startDate != null && endDate != null) {
            pajakList = pajakRepository.findByTanggalpembuatanBetween(startDate, endDate);
        } else if (startDate != null) {
            pajakList = pajakRepository.findByTanggalpembuatanAfter(startDate);
        } else if (endDate != null) {
            pajakList = pajakRepository.findByTanggalpembuatanBefore(endDate);
        } else {
            pajakList = pajakRepository.findAll();
        }

        List<PajakNasabahDTO.PajakDTO> pajakDTOs = pajakList.stream()
                .filter(p -> p.getCif().equals(cif))
                .map(this::convertToPajakDTO)
                .collect(Collectors.toList());

        PajakNasabahDTO response = new PajakNasabahDTO();
        response.setCif(nasabah.getCif());
        response.setNpwp(nasabah.getNpwp());
        response.setNama(nasabah.getNama());
        response.setAlamat(nasabah.getAlamat());
        response.setKota(nasabah.getKota());
        response.setBanknpwp(nasabah.getBanknpwp());
        response.setNamabank(nasabah.getNamabank());
        response.setEmail(nasabah.getEmail());
        response.setNamaofficetax(nasabah.getNamaofficetax());
        response.setPajak(pajakDTOs);

        return response;
    }
    
    private PajakNasabahDTO.PajakDTO convertToPajakDTO(Pajak pajak) {
    	PajakNasabahDTO.PajakDTO dto = new PajakNasabahDTO.PajakDTO();
        dto.setTableId(pajak.getTableId());
        dto.setCif(pajak.getCif());
        dto.setJenispenghasilan(pajak.getJenispenghasilan());
        dto.setJumlahbunga(pajak.getJumlahbunga());
        dto.setTarif(pajak.getTarif());
        dto.setPphpotong(pajak.getPphpotong());
        dto.setDnln(pajak.getDnln());
        dto.setTanggalpembuatan(pajak.getTanggalpembuatan());
        dto.setStatus(pajak.getStatus());
        return dto;
    }
    
    @Override
    public List<StatusDeliveryDTO> getStatusDelivery(LocalDate startDate, LocalDate endDate, String status) {
        List<LogPajak> logPajakList;

        if (startDate != null && endDate != null) {
            logPajakList = logPajakRepository.findByLogGenerateDateBetween(startDate, endDate)
                    .stream()
                    .filter(lp -> status == null || lp.getLogStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        } else if (startDate != null) {
            logPajakList = logPajakRepository.findByLogGenerateDateAfter(startDate)
                    .stream()
                    .filter(lp -> status == null || lp.getLogStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        } else if (endDate != null) {
            logPajakList = logPajakRepository.findByLogGenerateDateBefore(endDate)
                    .stream()
                    .filter(lp -> status == null || lp.getLogStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        } else {
            logPajakList = logPajakRepository.findAll()
                    .stream()
                    .filter(lp -> status == null || lp.getLogStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }

        return logPajakList.stream().map(logPajak -> {
            StatusDeliveryDTO dto = new StatusDeliveryDTO();
            dto.setNo(logPajakList.indexOf(logPajak) + 1);
            dto.setCif(logPajak.getLogCif());
            dto.setTanggalpembuatan(logPajak.getLogSendDate());
            dto.setStatus(logPajak.getLogStatus());
            dto.setMessage(logPajak.getLogMessage());

            Nasabah nasabah = nasabahRepository.findById(logPajak.getLogCif())
                    .orElseThrow(() -> new RuntimeException("Nasabah not found"));
            dto.setEmail(nasabah.getEmail());

            return dto;
        }).collect(Collectors.toList());
    }

    
    @Override
    public TaxSummaryDTO getTaxSummary(String cif, LocalDate startDate, LocalDate endDate) {
        Nasabah nasabah = nasabahRepository.findById(cif)
                .orElseThrow(() -> new RuntimeException("Nasabah not found"));

        List<Pajak> pajakList;
        if (startDate != null && endDate != null) {
            pajakList = pajakRepository.findByTanggalpembuatanBetween(startDate, endDate)
                    .stream()
                    .filter(p -> p.getCif().equals(cif))
                    .collect(Collectors.toList());
        } else if (startDate != null) {
            pajakList = pajakRepository.findByTanggalpembuatanAfter(startDate)
                    .stream()
                    .filter(p -> p.getCif().equals(cif))
                    .collect(Collectors.toList());
        } else if (endDate != null) {
            pajakList = pajakRepository.findByTanggalpembuatanBefore(endDate)
                    .stream()
                    .filter(p -> p.getCif().equals(cif))
                    .collect(Collectors.toList());
        } else {
            pajakList = pajakRepository.findByCif(cif);
        }

        List<TaxSummaryDetailDTO> pajakDetails = pajakList.stream().map(pajak -> {
        	TaxSummaryDetailDTO dto = new TaxSummaryDetailDTO();
            dto.setNo(pajakList.indexOf(pajak) + 1);
            dto.setJenispenghasilan(pajak.getJenispenghasilan());
            dto.setJumlahbunga(pajak.getJumlahbunga());
            dto.setTarif(pajak.getTarif());
            dto.setPphpotong(pajak.getPphpotong());
            return dto;
        }).collect(Collectors.toList());

        BigDecimal totalPenghasilan = pajakDetails.stream()
                .map(TaxSummaryDetailDTO::getJumlahbunga)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalPajak = pajakDetails.stream()
                .map(TaxSummaryDetailDTO::getPphpotong)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        TaxSummaryDTO response = new TaxSummaryDTO();
        response.setCif(nasabah.getCif());
        response.setNama(nasabah.getNama());
        response.setAlamat(nasabah.getAlamat());
        response.setKota(nasabah.getKota());
        response.setPeriod(startDate + " - " + endDate);
        response.setPajak(pajakDetails);
        response.setTotalpenghasilan(totalPenghasilan);
        response.setTotalpajak(totalPajak);

        return response;
    }
    
    
}
