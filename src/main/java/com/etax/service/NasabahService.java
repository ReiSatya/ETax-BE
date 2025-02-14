package com.etax.service;

import java.util.List;
import java.util.Map;

import com.etax.dto.EtaxStatementDTO;
import com.etax.dto.NasabahDTO;

public interface NasabahService {
	void createNasabah(Map<String, Object> requestMap);
    List<NasabahDTO> getAllNasabah();
    List<EtaxStatementDTO> getNasabahEtaxStatement(String cif);
    
}
