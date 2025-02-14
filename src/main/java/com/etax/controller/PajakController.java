package com.etax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etax.dto.PajakNasabahDTO;
import com.etax.dto.StatusDeliveryDTO;
import com.etax.dto.TaxSummaryDTO;
import com.etax.model.Pajak;
import com.etax.repo.PajakRepo;
import com.etax.service.PajakService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PajakController {

    @Autowired
    private PajakRepo pajakRepository;
    
    @Autowired
    private PajakService pajakService;

    @GetMapping("/pajak")
    public ResponseEntity<List<Pajak>> getParams(
            @RequestParam(required = false) Long tableId,
            @RequestParam(required = false) String cif,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        try {
            List<Pajak> pajakList = pajakService.getPajak(tableId, cif, status, startDate, endDate);

            if (pajakList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(pajakList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/pajaknasabah")
    public ResponseEntity<PajakNasabahDTO> getPajakNasabah(
            @RequestParam String cif,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        PajakNasabahDTO response = pajakService.getPajakNasabah(cif, startDate, endDate);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/statusdelivery")
    public ResponseEntity<List<StatusDeliveryDTO>> getStatusDelivery(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String status) {
        List<StatusDeliveryDTO> response = pajakService.getStatusDelivery(startDate, endDate, status);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/summary")
    public ResponseEntity<TaxSummaryDTO> getTaxSummary(
            @RequestParam String cif,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    	TaxSummaryDTO response = pajakService.getTaxSummary(cif, startDate, endDate);
        return ResponseEntity.ok(response);
    }
    
    
}
