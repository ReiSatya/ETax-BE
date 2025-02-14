package com.etax.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etax.dto.EtaxStatementDTO;
import com.etax.dto.EtaxStatementDetailDTO;
import com.etax.dto.NasabahDTO;
import com.etax.model.Bank;
import com.etax.model.Nasabah;
import com.etax.model.Pajak;
import com.etax.repo.BankRepo;
import com.etax.repo.NasabahRepo;
import com.etax.repo.PajakRepo;
import com.etax.service.NasabahService;

@Service
public class NasabahServiceImpl implements NasabahService {
	
	private final NasabahRepo nasabahRepository;
    private final BankRepo bankRepository;
    private final PajakRepo pajakRepository;

    @Autowired
    public NasabahServiceImpl(NasabahRepo nasabahRepository, BankRepo bankRepository, PajakRepo pajakRepository) {
        this.nasabahRepository = nasabahRepository;
        this.bankRepository = bankRepository;
        this.pajakRepository = pajakRepository;
    }

    @Override
    @Transactional
    public void createNasabah(Map<String, Object> requestMap) {
        validateRequest(requestMap);

        Bank bank = validateAndGetBank(requestMap);

        Nasabah nasabah = new Nasabah();
        nasabah.setCif((String) requestMap.get("cif"));
        nasabah.setNpwp((String) requestMap.get("npwp"));
        nasabah.setNama((String) requestMap.get("nama"));
        nasabah.setAlamat((String) requestMap.get("alamat"));
        nasabah.setKota((String) requestMap.get("kota"));
        nasabah.setBanknpwp((String) requestMap.get("banknpwp"));
        nasabah.setNamabank((String) requestMap.get("namabank"));
        nasabah.setEmail((String) requestMap.get("email"));
        nasabah.setNamaofficetax((String) requestMap.get("namaofficetax"));

        nasabahRepository.save(nasabah);
    }

    private void validateRequest(Map<String, Object> requestMap) {
        if (requestMap.get("npwp") == null || ((String) requestMap.get("npwp")).isEmpty()) {
            throw new IllegalArgumentException("NPWP cannot be empty");
        }
        if (requestMap.get("nama") == null || ((String) requestMap.get("nama")).isEmpty()) {
            throw new IllegalArgumentException("Nama cannot be empty");
        }
        if (requestMap.get("alamat") == null || ((String) requestMap.get("alamat")).isEmpty()) {
            throw new IllegalArgumentException("Alamat cannot be empty");
        }
    }

    private Bank validateAndGetBank(Map<String, Object> requestMap) {
        String banknpwp = (String) requestMap.get("banknpwp");
        String namabank = (String) requestMap.get("namabank");

        if (banknpwp == null || banknpwp.isEmpty() || namabank == null || namabank.isEmpty()) {
            throw new IllegalArgumentException("Banknpwp and Namabank cannot be empty");
        }

        Optional<Bank> optionalBank = bankRepository.findByBanknpwpAndNamabank(banknpwp, namabank);
        if (optionalBank.isEmpty()) {
            throw new IllegalArgumentException("Invalid bank details: banknpwp and namabank combination not found.");
        }
        return optionalBank.get();
    }


    @Override
    public List<NasabahDTO> getAllNasabah() {
        List<Nasabah> nasabahList = nasabahRepository.findAll();

        return nasabahList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private NasabahDTO convertToDto(Nasabah nasabah) {
        return new NasabahDTO(
                nasabah.getCif(),
                nasabah.getNpwp(),
                nasabah.getNama(),
                nasabah.getAlamat()
        );
    }
    
    @Override
    public List<EtaxStatementDTO> getNasabahEtaxStatement(String cif) {
    	List<Nasabah> nasabahList;
    	if (cif != null && !cif.isEmpty()) {
            Optional<Nasabah> nasabahOptional = nasabahRepository.findByCif(cif);
            nasabahList = nasabahOptional.map(List::of).orElse(List.of());
        } else {
            nasabahList = nasabahRepository.findAll();
        }

        return nasabahList.stream()
                .map(this::convertToNasabahPajakDTO)
                .collect(Collectors.toList());
    }

    private EtaxStatementDTO convertToNasabahPajakDTO(Nasabah nasabah) {
    	EtaxStatementDTO dto = new EtaxStatementDTO();
    	dto.setCif(nasabah.getCif());
        dto.setNpwp(nasabah.getNpwp());
        dto.setNama(nasabah.getNama());
        dto.setAlamat(nasabah.getAlamat());

        List<Pajak> pajakList = pajakRepository.findByCif(nasabah.getCif());
        List<EtaxStatementDetailDTO> pajakDTOList = pajakList.stream()
                .map(this::convertToPajakDTO)
                .collect(Collectors.toList());

        dto.setPajak(pajakDTOList);
        return dto;
    }

    private EtaxStatementDetailDTO convertToPajakDTO(Pajak pajak) {
    	EtaxStatementDetailDTO pajakDTO = new EtaxStatementDetailDTO();
        pajakDTO.setJenispenghasilan(pajak.getJenispenghasilan());
        pajakDTO.setJumlahbunga(pajak.getJumlahbunga());
        pajakDTO.setTarif(pajak.getTarif());
        pajakDTO.setPphpotong(pajak.getPphpotong());
        pajakDTO.setDnln(pajak.getDnln());
        return pajakDTO;
    }

}
