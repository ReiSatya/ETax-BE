package com.etax.service;

import java.util.List;

import com.etax.dto.LogPajakNasabahDTO;

public interface LogPajakService {

    public List<LogPajakNasabahDTO> getLogPajakByCif(String cif);

}
