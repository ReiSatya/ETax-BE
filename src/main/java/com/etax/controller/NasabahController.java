package com.etax.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etax.dto.ApiResponse;
import com.etax.dto.EtaxStatementDTO;
import com.etax.dto.NasabahDTO;
import com.etax.model.Nasabah;
import com.etax.repo.NasabahRepo;
import com.etax.service.NasabahService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class NasabahController {
		
	@Autowired
    private NasabahRepo nasabahRepository;
	
	private final NasabahService nasabahService;

    @Autowired
    public NasabahController(NasabahService nasabahService) {
        this.nasabahService = nasabahService;
    }

	@PostMapping("/addnasabah")
	public ResponseEntity<Object> createNasabah(@RequestBody Map<String, Object> requestMap) {
        try {
            nasabahService.createNasabah(requestMap);
            return ResponseEntity.status(HttpStatus.CREATED).body("Nasabah created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Nasabah.");
        }
    }
	
	@GetMapping("/nasabah")
    public ResponseEntity<List<Nasabah>> searchNasabah(
            @RequestParam(required = false) String cif,
            @RequestParam(required = false) String npwp,
            @RequestParam(required = false) String nama,
            @RequestParam(required = false) String banknpwp,
            @RequestParam(required = false) String namabank) {
        try {
            List<Nasabah> nasabahList = nasabahRepository.findByParams(cif, npwp, nama, banknpwp, namabank);
            if (nasabahList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(nasabahList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	@GetMapping("/data/nasabah")
	    public ResponseEntity<ApiResponse> getAllNasabah() {
	        List<NasabahDTO> nasabahDTOs = nasabahService.getAllNasabah();
	        ApiResponse response = new ApiResponse("Success", nasabahDTOs);
	        return ResponseEntity.ok(response);
	    }

	 @GetMapping("/data/etax")
	 public Map<String, Object> getAllNasabahWithPajak(@RequestParam(required = false) String cif) {
	        List<EtaxStatementDTO> nasabahList = nasabahService.getNasabahEtaxStatement(cif);

	        Map<String, Object> response = new HashMap<>();
	        if (nasabahList.isEmpty()) {
	            response.put("Message", "Not Found");
	        } else {
	            response.put("Message", "Success");
	        }
	        response.put("Data", nasabahList);

	        return response;
	    }
}


