package com.etax.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import com.etax.model.Nasabah;
import com.etax.repo.NasabahRepo;
import com.etax.service.NasabahService;
import com.etax.service.ParameterService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PdfDownloadController {
	
	 	@Autowired
	    private NasabahRepo nasabahRepository;

	    @Autowired
	    private ParameterService parameterService;

	    @GetMapping("/downloadPdf")
	    public ResponseEntity<InputStreamResource> downloadPdf(
	            @RequestParam String cif,
	            @RequestParam String startDate,
	            @RequestParam(required = false) String endDate) throws IOException {

	        YearMonth startYearMonth = parseYearMonth(startDate);
	        YearMonth endYearMonth = endDate != null ? parseYearMonth(endDate) : startYearMonth;

	        validateDateRange(startYearMonth, endYearMonth);

	        Nasabah nasabah = getNasabahByCif(cif);
	        String nasabahName = nasabah.getNama();

	        String pdfDirectory = parameterService.getPdfPathReport();
	        List<File> pdfFiles = retrievePdfFiles(pdfDirectory, startYearMonth, endYearMonth, nasabahName);

	        if (pdfFiles.size() == 1) {
	            File file = pdfFiles.get(0);
	            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	            HttpHeaders headers = new HttpHeaders();
	            headers.add("Content-Disposition", "attachment; filename=" + file.getName());
	            return ResponseEntity.ok()
	                    .headers(headers)
	                    .contentLength(file.length())
	                    .contentType(MediaType.APPLICATION_PDF)
	                    .body(resource);
	        }

	        File zipFile = createZipFile(pdfFiles, nasabahName);

	        InputStreamResource resource = new InputStreamResource(new FileInputStream(zipFile));
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=" + zipFile.getName());
	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(zipFile.length())
	                .contentType(MediaType.parseMediaType("application/zip"))
	                .body(resource);
	    }
	    
	    @GetMapping("/downloadSummaryPdf")
	    public ResponseEntity<InputStreamResource> downloadSummaryPdf(
	            @RequestParam String cif,
	            @RequestParam String startDate,
	            @RequestParam(required = false) String endDate) throws IOException {

	        YearMonth startYearMonth = parseYearMonth(startDate);
	        YearMonth endYearMonth = endDate != null ? parseYearMonth(endDate) : startYearMonth;

	        validateDateRange(startYearMonth, endYearMonth);

	        Nasabah nasabah = getNasabahByCif(cif);
	        String nasabahName = nasabah.getNama();

	        String pdfDirectory = parameterService.getPdfPathReport();
	        List<File> pdfFiles = retrieveSummaryPdfFiles(pdfDirectory, startYearMonth, endYearMonth, nasabahName);

	        if (pdfFiles.size() == 1) {
	            File file = pdfFiles.get(0);
	            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	            HttpHeaders headers = new HttpHeaders();
	            headers.add("Content-Disposition", "attachment; filename=" + file.getName());
	            return ResponseEntity.ok()
	                    .headers(headers)
	                    .contentLength(file.length())
	                    .contentType(MediaType.APPLICATION_PDF)
	                    .body(resource);
	        }

	        File zipFile = createZipFile(pdfFiles, nasabahName);

	        InputStreamResource resource = new InputStreamResource(new FileInputStream(zipFile));
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=" + zipFile.getName());
	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(zipFile.length())
	                .contentType(MediaType.parseMediaType("application/zip"))
	                .body(resource);
	    }

	    private YearMonth parseYearMonth(String yearMonthString) {
	        try {
	            return YearMonth.parse(yearMonthString);
	        } catch (Exception e) {
	            throw new IllegalArgumentException("Invalid year-month format. Use format YYYY-MM.");
	        }
	    }

	    private void validateDateRange(YearMonth startYearMonth, YearMonth endYearMonth) {
	        if (startYearMonth.isAfter(endYearMonth)) {
	            throw new IllegalArgumentException("Start date cannot be after end date.");
	        }
	    }

	    public Nasabah getNasabahByCif(String cif) {
	        Optional<Nasabah> nasabahOptional = nasabahRepository.findByCif(cif);
	        if (nasabahOptional.isEmpty()) {
	            throw new RuntimeException("Nasabah not found for CIF: " + cif);
	        }
	        return nasabahOptional.get();
	    }

	    private List<File> retrievePdfFiles(String pdfDirectory, YearMonth startYearMonth, YearMonth endYearMonth, String nasabahName) {
	        List<File> pdfFiles = new ArrayList<>();
	        for (YearMonth ym = startYearMonth; !ym.isAfter(endYearMonth); ym = ym.plusMonths(1)) {
	            String yearMonthDirectory = String.format("%02d", ym.getMonthValue()) + ym.getYear();
	            String filename = nasabahName + ".pdf";
	            File file = new File(pdfDirectory + File.separator + yearMonthDirectory + File.separator + filename);
	            if (file.exists()) {
	                pdfFiles.add(file);
	            }
	        }
	        if (pdfFiles.isEmpty()) {
	            throw new RuntimeException("No PDF files found for the specified date range.");
	        }
	        return pdfFiles;
	    }
	    
	    private List<File> retrieveSummaryPdfFiles(String pdfDirectory, YearMonth startYearMonth, YearMonth endYearMonth, String nasabahName) {
	        List<File> pdfFiles = new ArrayList<>();
	        for (YearMonth ym = startYearMonth; !ym.isAfter(endYearMonth); ym = ym.plusMonths(1)) {
	            String yearMonthDirectory = String.format("%02d", ym.getMonthValue()) + ym.getYear();
	            String filename = "Summary_" + nasabahName + ".pdf";
	            File file = new File(pdfDirectory + File.separator + yearMonthDirectory + File.separator + filename);
	            if (file.exists()) {
	                pdfFiles.add(file);
	            }
	        }
	        if (pdfFiles.isEmpty()) {
	            throw new RuntimeException("No PDF files found for the specified date range.");
	        }
	        return pdfFiles;
	    }

	    private File createZipFile(List<File> pdfFiles, String nasabahName) throws IOException {
	    	nasabahName = nasabahName.replaceAll("[\\\\/:*?\"<>|]", "_");

	        File zipFile = new File(System.getProperty("java.io.tmpdir") + File.separator + nasabahName + ".zip");
	        try (FileOutputStream fos = new FileOutputStream(zipFile);
	             ZipOutputStream zos = new ZipOutputStream(fos)) {

	            Set<String> usedNames = new HashSet<>();
	            int fileCounter = 1;

	            for (File pdfFile : pdfFiles) {
	                String originalName = pdfFile.getName();
	                String entryName = originalName;

	                int dotIndex = originalName.lastIndexOf('.');
	                String baseName = originalName.substring(0, dotIndex);
	                String extension = originalName.substring(dotIndex);

	                while (usedNames.contains(entryName)) {
	                    entryName = baseName + "_" + fileCounter + extension;
	                    fileCounter++;
	                }

	                usedNames.add(entryName);

	                ZipEntry zipEntry = new ZipEntry(entryName);
	                zos.putNextEntry(zipEntry);
	                try (FileInputStream fis = new FileInputStream(pdfFile)) {
	                    IOUtils.copy(fis, zos);
	                }
	                zos.closeEntry();
	            }
	        }
	        return zipFile;
	    }
}
