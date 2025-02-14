package com.etax.service;

import java.time.LocalDate;
import java.util.List;

import com.etax.dto.EtaxStatementDTO;
import com.etax.dto.NasabahDTO;
import com.etax.dto.PajakNasabahDTO;
import com.etax.dto.StatusDeliveryDTO;
import com.etax.dto.TaxSummaryDTO;
import com.etax.model.Pajak;

public interface PajakService {
	List<Pajak> getPajak(Long tableId, String cif, String status, LocalDate startDate, LocalDate endDate);
	PajakNasabahDTO getPajakNasabah(String cif, LocalDate startDate, LocalDate endDate);
	List<StatusDeliveryDTO> getStatusDelivery(LocalDate startDate, LocalDate endDate, String status);
	TaxSummaryDTO getTaxSummary(String cif, LocalDate startDate, LocalDate endDate);
	
}
